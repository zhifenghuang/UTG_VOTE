package com.hilamg.api.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hilamg.api.dto.in.AddProposal;
import com.hilamg.api.dto.in.ProposalOptions;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.IUserAddressService;
import com.hilamg.api.service.IUserProposalService;
import com.hilamg.auth.exception.UnauthorizedException;
import com.hilamg.auth.jwt.UserTokenManager;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.redis.RedisUtil;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 提案列表 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-26
 */
@RestController
@RequestMapping("/api/userProposal")
public class UserProposalController {

    @Autowired
    IUserProposalService userProposalService;

    @Autowired
    IUserAddressService addressService;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping("addProposal")
    @LogAnnotation(operateType = "提案", operateContent = "发起提案")
    public Result addProposal(@LoginUserAnnotation UserAddress userAddress, @Valid AddProposal addProposal) {
        //将json字符串转为json数组
        JSONArray json= JSONArray.parseArray(addProposal.getProposalOptions());
        //将json数组转为list集合
        List<ProposalOptions> prots=JSONArray.parseArray(json.toString(), ProposalOptions.class);
        return userProposalService.addProposal(userAddress, addProposal,prots);
    }

    @GetMapping("getProposalList")
    @LogAnnotation(operateType = "提案", operateContent = "获取提案列表")
    public Result getProposalList(@LoginUserAnnotation UserAddress userAddress,String status,Integer spaceId) {
        return userProposalService.getProposalList(userAddress,status,spaceId);
    }

    @GetMapping("getProposalInfo")
    @LogAnnotation(operateType = "提案", operateContent = "获取提案详情")
    public Result getProposalInfo(@LoginUserAnnotation UserAddress userAddress,Integer id) {
        return userProposalService.getProposalInfo(userAddress,id);
    }

    @GetMapping("getTimeLineList")
    @LogAnnotation(operateType = "提案", operateContent = "获取提案时间线")
    public Result getTimeLineList(HttpServletRequest request, String type, String status) {
        UserAddress userAddress;
        if(type.equals("1")){
            String token = request.getHeader("token");
            if (token == null || token.isEmpty()) {
                throw new UnauthorizedException();
            }
            Integer userId = UserTokenManager.getUserId(token);
            if (null == userId) {
                throw new UnauthorizedException();
            }
            Object object = redisUtil.hget("token", String.valueOf(userId));
            if (!token.equals(object)) {
                throw new UnauthorizedException();
            }
             userAddress = addressService.getById(userId);
            if(null==userAddress){
                throw new UnauthorizedException();
            }
        }else{
            userAddress=new UserAddress();
            userAddress.setId(0);
        }
        return userProposalService.getTimeLineList(userAddress,type,status);
    }

    @GetMapping("getUserProposalLog")
    @LogAnnotation(operateType = "提案", operateContent = "获取用户投票列表")
    public Result getUserProposalLog(@LoginUserAnnotation UserAddress userAddress) {
        return userProposalService.getUserProposalLog(userAddress);
    }

    @GetMapping("updateProposalOnLineStatus")
    @LogAnnotation(operateType = "提案", operateContent = "查看提案是否上链")
    public Result updateProposalOnLineStatus() {
        return userProposalService.updateProposalOnLineStatus();
    }

    @GetMapping("addProposalLogs")
    @LogAnnotation(operateType = "提案", operateContent = "检索投票记录")
    public Result addProposalLogs() throws ExecutionException, InterruptedException {
        return userProposalService.addProposalLogs();
    }

    @GetMapping("checkProposalStatus")
    @LogAnnotation(operateType = "提案", operateContent = "定时判断投票日期")
    public Result checkProposalStatus(){
        return userProposalService.checkProposalStatus();
    }

}
