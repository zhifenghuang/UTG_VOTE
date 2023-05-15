package com.hilamg.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hilamg.api.dto.in.CreateSpace;
import com.hilamg.api.dto.in.UpdateSpace;
import com.hilamg.api.dto.out.SpaceInfo;
import com.hilamg.api.dto.out.SpaceList;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserJoinSpace;
import com.hilamg.api.entity.UserSpace;
import com.hilamg.api.mapper.UserAddressMapper;
import com.hilamg.api.mapper.UserJoinSpaceMapper;
import com.hilamg.api.mapper.UserSpaceMapper;
import com.hilamg.api.mapper.UserSpaceTypeMapper;
import com.hilamg.api.service.IUserSpaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hilamg.common.constant.ProConst;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import com.hilamg.common.utils.bscUtils.Environment;
import com.hilamg.common.utils.bscUtils.TokenClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户空间 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Service
public class UserSpaceServiceImpl extends ServiceImpl<UserSpaceMapper, UserSpace> implements IUserSpaceService {

    @Autowired
    UserSpaceMapper userSpaceMapper;

    @Autowired
    UserJoinSpaceMapper userJoinSpaceMapper;

    @Autowired
    UserAddressMapper userAddressMapper;

    private static Web3j web3j;

    @Override
    public Result createSpace(UserAddress userAddress, CreateSpace createSpace) {
//        int count = userSpaceMapper.selectCount(new LambdaQueryWrapper<UserSpace>()
//                .eq(UserSpace::getContractAddress, createSpace.getContractAddress())
//                .eq(UserSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
//        if (count > 0) {
//            return ResultGenerator.genFailResult("has_space");
//        }
        UserSpace userSpace = new UserSpace();
        BeanUtils.copyProperties(createSpace, userSpace);
        userSpace.setCreateDate(new Date()).setOwnerId(userAddress.getId());
        userSpaceMapper.insert(userSpace);
        UserJoinSpace userJoinSpace = new UserJoinSpace();
        userJoinSpace.setUserId(userAddress.getId())
                .setSpace(userSpace.getId())
                .setCreateDate(new Date())
                .setIsOwer(ProConst.is_ower.ower.getValue());
        userJoinSpaceMapper.insert(userJoinSpace);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result updateSpace(UserAddress userAddress, UpdateSpace updateSpace) {
        UserSpace userSpace = userSpaceMapper.selectOne(new LambdaQueryWrapper<UserSpace>()
                .eq(UserSpace::getId, updateSpace.getId())
                .eq(UserSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
        if (null == userSpace) {
            return ResultGenerator.genFailResult("not_has_space");
        }
//        int count = userSpaceMapper.selectCount(new LambdaQueryWrapper<UserSpace>()
//                .eq(UserSpace::getContractAddress, updateSpace.getContractAddress())
//                .ne(UserSpace::getId, updateSpace.getId())
//                .eq(UserSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
//        if (count > 0) {
//            return ResultGenerator.genFailResult("has_space");
//        }
        if (userAddress.getId() != userSpace.getOwnerId()) {
            return ResultGenerator.genFailResult("not_is_owner");
        }
        //修改
        BeanUtils.copyProperties(updateSpace, userSpace);
        userSpaceMapper.updateById(userSpace);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result getSpaceList(UserAddress userAddress, String type, String name) {
        List<SpaceList> spaces = userSpaceMapper.getSpaceList(type, name);
        spaces.forEach(space -> {
            int number = userJoinSpaceMapper.selectCount(new LambdaQueryWrapper<UserJoinSpace>()
                    .eq(UserJoinSpace::getSpace, space.getId())
                    .eq(UserJoinSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
            space.setNumber(number);
            int inOwner = userJoinSpaceMapper.selectCount(new LambdaQueryWrapper<UserJoinSpace>()
                    .eq(UserJoinSpace::getSpace, space.getId())
                    .eq(UserJoinSpace::getUserId, userAddress.getId())
                    .eq(UserJoinSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
            if (inOwner > 0) {
                //已加入
                space.setIsJson(ProConst.is_join.has_join.getValue());
            } else {
                //未加入
                space.setIsJson(ProConst.is_join.not_join.getValue());
            }
        });
        return ResultGenerator.genSuccessResult(spaces);
    }

    @Override
    public Result getSpaceInfo(UserAddress userAddress, Integer id) {
        UserSpace userSpace = userSpaceMapper.selectById(id);
        if (null == userSpace) {
            return ResultGenerator.genFailResult("not_has_space");
        }
        UserAddress ownerUserAddress = userAddressMapper.selectById(userSpace.getOwnerId());
        SpaceInfo spaceInfo = new SpaceInfo();
        BeanUtils.copyProperties(userSpace, spaceInfo);
        spaceInfo.setOwner(ownerUserAddress.getAddress());
        int inOwner = userJoinSpaceMapper.selectCount(new LambdaQueryWrapper<UserJoinSpace>()
                .eq(UserJoinSpace::getSpace, id)
                .eq(UserJoinSpace::getUserId, userAddress.getId())
                .eq(UserJoinSpace::getIsDelete, ProConst.DeleteEnum.NODELETE.getValue()));
        if (inOwner > 0) {
            //已加入
            spaceInfo.setIsJson(ProConst.is_join.has_join.getValue());
        } else {
            //未加入
            spaceInfo.setIsJson(ProConst.is_join.not_join.getValue());
        }
        return ResultGenerator.genSuccessResult(spaceInfo);
    }

    @Override
    public Result getContractAddressInfo(String address) {
        String profex=address.substring(0,2);
        if(profex.equals("ux") ||profex.equals("Ux")){
            address="0x"+address.substring(2,address.length());
        }
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if ("0x0000000000000000000000000000000000000000".equals(address)) {
                map.put("decimals", 18);
                map.put("symbol", "UTG");
            } else {
                web3j = Web3j.build(new HttpService(Environment.RPC_URL));
                int decimals = TokenClient.getTokenDecimals(web3j, address);
                System.out.println("查询代币精度" + decimals);
                String symbol = TokenClient.getTokenSymbol(web3j, address);
                System.out.println("查询代币符号" + symbol);
                map.put("decimals", decimals);
                map.put("symbol", symbol);
            }
            return ResultGenerator.genSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("contractAddress_error");
        }
    }
}
