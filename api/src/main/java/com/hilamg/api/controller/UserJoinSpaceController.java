package com.hilamg.api.controller;


import com.hilamg.api.dto.in.CreateSpace;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.IUserJoinSpaceService;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户加入空间列表 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/api/userJoinSpace")
public class UserJoinSpaceController {

    @Autowired
    IUserJoinSpaceService userJoinSpaceService;


    @PostMapping("joinSpace")
    @LogAnnotation(operateType = "空间", operateContent = "加入空间")
    public Result joinSpace(@LoginUserAnnotation UserAddress userAddress,Integer  spaceId) {
        return userJoinSpaceService.joinSpace(userAddress, spaceId);
    }

    @GetMapping("userSpaceList")
    @LogAnnotation(operateType = "空间", operateContent = "用户加入空间列表")
    public Result userSpaceList(@LoginUserAnnotation UserAddress userAddress) {
        return userJoinSpaceService.userSpaceList(userAddress);
    }

    @PostMapping("exitSpace")
    @LogAnnotation(operateType = "空间", operateContent = "退出空间")
    public Result exitSpace(@LoginUserAnnotation UserAddress userAddress,Integer  spaceId) {
        return userJoinSpaceService.exitSpace(userAddress, spaceId);
    }


}
