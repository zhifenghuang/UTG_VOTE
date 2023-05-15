package com.hilamg.api.controller;


import com.hilamg.api.dto.in.UpUserInfo;
import com.hilamg.api.dto.out.UserInfo;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.IUserAddressService;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户地址信息 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/api/address")
public class UserAddressController {

    @Autowired
    IUserAddressService addressService;

    @PutMapping("getToken")
    @LogAnnotation(operateType = "地址相关", operateContent = "获取Token")
    public Result getToken(String adddres){
      return addressService.getToken(adddres);
    }

    @PutMapping("updateUserInfo")
    @LogAnnotation(operateType = "用户相关", operateContent = "编辑个人资料")
    public Result updateUserInfo(@Valid UpUserInfo upUserInfo,@LoginUserAnnotation UserAddress userAddress){
        return addressService.updateUserInfo(userAddress,upUserInfo);
    }

    @GetMapping("getUserInfo")
    @LogAnnotation(operateType = "用户相关", operateContent = "编辑个人资料")
    public Result getUserInfo(@LoginUserAnnotation UserAddress userAddress){
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userAddress, userInfo);
        return ResultGenerator.genSuccessResult(userInfo);
    }
}
