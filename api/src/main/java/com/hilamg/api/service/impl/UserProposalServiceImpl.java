package com.hilamg.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hilamg.api.dto.in.AddProposal;
import com.hilamg.api.dto.out.*;
import com.hilamg.api.entity.*;
import com.hilamg.api.mapper.*;
import com.hilamg.api.service.ITbSysDictService;
import com.hilamg.api.service.IUserProposalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hilamg.common.constant.ProConst;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import com.hilamg.common.utils.bscUtils.BscSign;
import com.hilamg.common.utils.bscUtils.Environment;
import com.hilamg.common.utils.bscUtils.TokenClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.StaticArray;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 提案列表 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-26
 */
@Slf4j
@Service
@Transactional
public class UserProposalServiceImpl extends ServiceImpl<UserProposalMapper, UserProposal> implements IUserProposalService {

    @Autowired
    UserProposalMapper userProposalMapper;

    @Autowired
    UserSpaceMapper userSpaceMapper;

    @Autowired
    UserProposalOptionsMapper userProposalOptionsMapper;

    @Autowired
    ITbSysDictService sysDictService;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    UserProposalLogMapper userProposalLogMapper;

    private static Web3j web3j;

    @Autowired
    private UserJoinSpaceMapper userJoinSpaceMapper;

    @Override
    public Result addProposal(UserAddress userAddress, AddProposal addProposal, List<com.hilamg.api.dto.in.ProposalOptions> proposalOptions) {
//        BigDecimal proposalFee = new BigDecimal(sysDictService.getSysDict(ProConst.dictKey.proposal_fee.getValue()));
        //查看该空间是否存在
        UserSpace userSpace = userSpaceMapper.selectById(addProposal.getSpaceId());
        if (null == userSpace) {
            return ResultGenerator.genFailResult("not_has_space");
        }
        //判断是否已加入该空间
        UserJoinSpace userJoinSpace= userJoinSpaceMapper.selectOne(new LambdaQueryWrapper<UserJoinSpace>()
                .eq(UserJoinSpace::getUserId,userAddress.getId())
                .eq(UserJoinSpace::getIsDelete,ProConst.DeleteEnum.NODELETE.getValue())
                .eq(UserJoinSpace::getSpace,addProposal.getSpaceId()).last("limit 1"));
        if(null==userJoinSpace){
            return ResultGenerator.genFailResult("not_join_space");
        }
        //判断选项内容
        //添加提案记录
        UserProposal userProposal = new UserProposal();
//        Long nowTime=System.currentTimeMillis();
//        if(nowTime<=addProposal.getStartTime() * 1000){
//            userProposal.setStatus(ProConst.proposalStatus.has_open.getValue());
//        }
        BeanUtils.copyProperties(addProposal, userProposal);
        userProposal.setProposalUser(userAddress.getId())
                .setTokenAmount(userSpace.getTokenAmount())
                .setStartTime(new Date(addProposal.getStartTime() * 1000))
                .setEndTime(new Date(addProposal.getEndTime() * 1000))
                .setCreateDate(new Date());
        userProposalMapper.insert(userProposal);
        List<String> ops = new ArrayList<String>();
        proposalOptions.stream().forEach(options -> {
            UserProposalOptions userProposalOptions = new UserProposalOptions();
            userProposalOptions.setProposalId(userProposal.getId())
                    .setCreateDate(new Date())
                    .setOptions(options.getOptions());
            userProposalOptionsMapper.insert(userProposalOptions);
            ops.add(userProposalOptions.getOptions());
        });
        //todo  根据内容做签名
        StringBuilder msg = new StringBuilder();
        //提案id
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(userProposal.getId()))));
        //kind 投票类型
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(addProposal.getKind()))));
        //spaceId 空间id
        msg.append(TypeEncoder.encode(new Uint32(Long.valueOf(addProposal.getSpaceId()))));
        //user 提案发起者
        Address ownerAddress = new Address(userAddress.getAddress());
        msg.append(TypeEncoder.encode(ownerAddress));
        String profex=userSpace.getContractAddress().substring(0,2);
        if(profex.equals("ux") ||profex.equals("Ux")){
          String  adddres="0x"+userSpace.getContractAddress().substring(2,userSpace.getContractAddress().length());
            userSpace.setContractAddress(adddres);
        }
        Address token = new Address(userSpace.getContractAddress());
        msg.append(TypeEncoder.encode(token));
        //提案消耗代币数量
        String tokenAmount = (userProposal.getTokenAmount().multiply(BigDecimal.TEN.pow(userSpace.getDecimals()))).setScale(0).toString();
        msg.append(TypeEncoder.encode(new Uint256(new BigInteger(tokenAmount))));
        //提案开始时间
//        msg.append(TypeEncoder.encode(new Uint256(Long.valueOf(addProposal.getStartTime()))));
//        //提案结束时间
//        msg.append(TypeEncoder.encode(new Uint256(Long.valueOf(addProposal.getEndTime()))));
        //选项数组
//        String away= JSONObject.toJSONString(ops);
//        msg.append(ops);
        String sign = BscSign.sign(msg);
        ProposalSignInfo proposalSignInfo = new ProposalSignInfo();
        proposalSignInfo.setEndTime(addProposal.getEndTime().toString())
                .setStartTime(addProposal.getStartTime().toString())
                .setKind(addProposal.getKind())
                .setVoteType(addProposal.getVoteType())
                .setProposalId(userProposal.getId().toString())
                .setToken(userSpace.getContractAddress())
                .setTokenAmount(tokenAmount)
                .setUser(userAddress.getAddress())
                .setOptions(ops)
                .setSpaceId(userSpace.getId().toString())
                .setSignature(sign);
        return ResultGenerator.genSuccessResult(proposalSignInfo);
    }

    @Override
    public Result getProposalList(UserAddress userAddress, String status, Integer spaceId) {
        List<UserProposalList> userProposalList = userProposalMapper.getUserProposalList(userAddress.getId(), spaceId, status);
        return ResultGenerator.genSuccessResult(userProposalList);
    }

    @Override
    public Result getProposalInfo(UserAddress userAddress, Integer id) {
        //获取投票信息
        UserProposal userProposal = userProposalMapper.selectById(id);
        if (null == userProposal) {
            return ResultGenerator.genFailResult("proposal_is_null");
        }
        //根据空间id获取币种
        UserSpace userSpace = userSpaceMapper.selectById(userProposal.getSpaceId());
        if (null == userSpace) {
            return ResultGenerator.genFailResult("not_has_space");
        }
        //获取提案发起者用户地址
        UserAddress userAddressInfo = userAddressMapper.selectById(userProposal.getProposalUser());
        //构建返回对象
        ProposalInfo proposalInfo = new ProposalInfo();
        BeanUtils.copyProperties(userProposal, proposalInfo);
        proposalInfo.setProposalUser(userAddressInfo.getAddress())
                .setSymbol(userSpace.getSymbol());
        //获取投票统计
        List<ProposalOptions> proposalOptionList = new ArrayList<ProposalOptions>();
        List<UserProposalOptions> userProposalOptionsList = userProposalOptionsMapper.selectList(new LambdaQueryWrapper<UserProposalOptions>()
                .eq(UserProposalOptions::getProposalId, id)
                .eq(UserProposalOptions::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
        userProposalOptionsList.stream().forEach(userProposalOptions -> {
            ProposalOptions proposalOptions = new ProposalOptions();
            //根据投票id统计票数
            BigDecimal sumAmount = userProposalLogMapper.getSumAmountById(userProposalOptions.getId()).divide(userProposal.getBili(),2,BigDecimal.ROUND_DOWN);
            proposalOptions.setId(userProposalOptions.getId())
                    .setAccount(sumAmount)
                    .setOptions(userProposalOptions.getOptions());
            proposalOptionList.add(proposalOptions);
        });
        proposalInfo.setProposalOptions(proposalOptionList);
        //获取投票用户列表
        List<ProposalLogList> proposalLogList = userProposalLogMapper.getLogs(id,userAddress.getId());
        proposalLogList.stream().forEach(proposalLogList1 -> {
            proposalLogList1.setAmount(proposalLogList1.getAmount().divide(userProposal.getBili(),2,BigDecimal.ROUND_DOWN));
        });
        proposalInfo.setProposalLogList(proposalLogList);
        //获取用户待赎回金额
        BigDecimal amountRedeemed = userProposalLogMapper.getMoney(id, userAddress.getId());
        //获取空间地址
        proposalInfo.setAmountRedeemed(amountRedeemed);
        proposalInfo.setSpaceAddress(userSpace.getContractAddress());
        return ResultGenerator.genSuccessResult(proposalInfo);
    }

    @Override
    public Result getTimeLineList(UserAddress userAddress, String type, String status) {
        List<UserProposalList> userProposalList = userProposalMapper.getTimeLineList(userAddress.getId(), type, status);
        return ResultGenerator.genSuccessResult(userProposalList);
    }

    @Override
    public Result getUserProposalLog(UserAddress userAddress) {
        List<UserOptionsLogs> userOptionsLogs = userProposalMapper.getproposalUserLog(userAddress.getId());
        return ResultGenerator.genSuccessResult(userOptionsLogs);
    }

    @Override
    public Result updateProposalOnLineStatus() {
        List<UserProposal> userOptionsLogs = userProposalMapper.selectList(new LambdaQueryWrapper<UserProposal>()
                .eq(UserProposal::getIsOneLine, ProConst.isOneLine.not_one_line.getValue()));
        web3j = Web3j.build(new HttpService(Environment.RPC_URL));
        for (UserProposal userProposal : userOptionsLogs) {
            Long id = Long.valueOf(userProposal.getId());
            Boolean ishas = TokenClient.getisExsitStatus(id, web3j);
            if (ishas) {
                userProposal.setIsOneLine(ProConst.isOneLine.has_one_line.getValue())
                        .setUpdateDate(new Date());
                userProposalMapper.updateById(userProposal);
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result addProposalLogs() throws ExecutionException, InterruptedException {
        List<UserProposal> userOptionsLogs = userProposalMapper.selectList(new LambdaQueryWrapper<UserProposal>()
                .eq(UserProposal::getIsOneLine, ProConst.isOneLine.has_one_line.getValue()));
        web3j = Web3j.build(new HttpService(Environment.RPC_URL));
        for (UserProposal userProposal : userOptionsLogs) {
            Long id = Long.valueOf(userProposal.getId());
            int counts = TokenClient.getCountByProposal(web3j, id);
//            List<Integer> opIds = userProposalOptionsMapper.getIdList(userProposal.getId());
            List<UserProposalOptions> opIds = userProposalOptionsMapper.selectList(new LambdaQueryWrapper<UserProposalOptions>()
                    .eq(UserProposalOptions::getProposalId,userProposal.getId()));
            log.info("提案{}投票总次数为{}", userProposal.getTitle(), counts);
            //获取空间地址
            UserSpace userSpace = userSpaceMapper.selectById(userProposal.getSpaceId());
            BigDecimal shuliang=new BigDecimal(Math.pow(Double.valueOf(10),Double.valueOf(userSpace.getDecimals())));
            //获取精度
            //获取提案选项id
            int i = 1;
            for (i = 1; i <= counts; i++) {
                WebProposalLog webProposalLog = TokenClient.getProposalLog(web3j, id, Long.valueOf(i));
                log.info("{}用户{}在提案{}的{}中发起了提案投票数量为{}",webProposalLog.getTime(),webProposalLog.getAddress(),userProposal.getTitle(),webProposalLog.getOption(),webProposalLog.getAmount());
                if (!webProposalLog.getAddress().equals("0x0000000000000000000000000000000000000000")) {
                    if (webProposalLog.getAmount()!=BigInteger.ZERO) {
                        UserProposalOptions userProposalOption = opIds.get(webProposalLog.getOption());
                        if(null==userProposalOption){
                            continue;
                        }
                        Integer opId=userProposalOption.getId();
                        BigDecimal amount=new BigDecimal(webProposalLog.getAmount()).divide(shuliang).setScale(2,BigDecimal.ROUND_DOWN);
                        //获取用户地址
                        UserAddress userAddress = userAddressMapper.selectOne(new LambdaQueryWrapper<UserAddress>()
                                .eq(UserAddress::getAddress, webProposalLog.getAddress()).last("limit 1"));
                        //判断投票是否已经添加上
                        int count = userProposalLogMapper.selectCount(new LambdaQueryWrapper<UserProposalLog>()
                                .eq(UserProposalLog::getProposalId, userProposal.getId())
                                .eq(UserProposalLog::getOptionsId, opId)
                                .eq(UserProposalLog::getAmount,amount)
                                .eq(UserProposalLog::getTimeTimeMillis, webProposalLog.getTime())
                        );
                        if (count > 0) {
                            log.info("该投票{}选项{}已经记录在数据库中", userProposal.getTitle(), opId);
                            continue;
                        }
                        //增加投票记录
                        UserProposalLog userProposalLog = new UserProposalLog();
                        userProposalLog.setProposalId(userProposal.getId())
                                .setUserId(userAddress.getId())
                                .setSpaceId(userProposal.getSpaceId())
                                .setOptionsId(opId)
                                .setAmount(amount)
                                .setTimeTimeMillis(webProposalLog.getTime())
                                .setCreateDate(new Date(webProposalLog.getTime()*1000L));
                        if (userProposal.getKind().equals("1")) {
                            //质押投票
                            userProposalLog.setStatus("2");
                        } else {
                            userProposalLog.setStatus("1");
                        }
                        userProposalLogMapper.insert(userProposalLog);
                    }
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result checkProposalStatus() {
        List<UserProposal> userOptionsLogs = userProposalMapper.selectList(new LambdaQueryWrapper<UserProposal>()
                .eq(UserProposal::getIsOneLine, ProConst.isOneLine.has_one_line.getValue()));
        //获取当前时间戳
        Long time=System.currentTimeMillis();
        //循环判断
        for(UserProposal userProposal:userOptionsLogs){
            Long startTime=userProposal.getCreateDate().getTime();
            Long endTime=userProposal.getEndTime().getTime();
            if(userProposal.getStatus().equals(ProConst.proposalStatus.not_open.getValue())){
                //待开始时间判断
                if(startTime<=time){
                    userProposal.setStatus(ProConst.proposalStatus.has_open.getValue());
                    log.info("提案{}已经开始",userProposal.getTitle());
                    userProposal.setUpdateDate(new Date());
                    userProposalMapper.updateById(userProposal);
                }
            }else if(userProposal.getStatus().equals(ProConst.proposalStatus.has_open.getValue())){
                //是否已结束判断
                if(endTime<=time){
                    userProposal.setStatus(ProConst.proposalStatus.end.getValue());
                    log.info("提案{}已经结束",userProposal.getTitle());
                    userProposal.setUpdateDate(new Date());
                    userProposalMapper.updateById(userProposal);
                }
            }
        }
        return ResultGenerator.genSuccessResult();
    }
}
