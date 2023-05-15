package com.hilamg.api.controller;


import com.hilamg.api.dto.in.CreateSpace;
import com.hilamg.api.dto.in.UpdateSpace;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserSpaceType;
import com.hilamg.api.service.IUserSpaceService;
import com.hilamg.api.service.IUserSpaceTypeService;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * <p>
 * 用户空间 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/api/userSpace")
public class UserSpaceController {

    @Autowired
    IUserSpaceService userSpaceService;

    @PostMapping("createSpace")
    @LogAnnotation(operateType = "空间", operateContent = "创建空间")
    public Result createSpace(@LoginUserAnnotation UserAddress userAddress, @Valid CreateSpace createSpace) {
        return userSpaceService.createSpace(userAddress, createSpace);
    }

    @PutMapping("updateSpace")
    @LogAnnotation(operateType = "空间", operateContent = "修改空间")
    public Result updateSpace(@LoginUserAnnotation UserAddress userAddress, @Valid UpdateSpace updateSpace) {
        return userSpaceService.updateSpace(userAddress, updateSpace);
    }

    @GetMapping("getSpaceList")
    @LogAnnotation(operateType = "空间", operateContent = "获取空间列表")
    public Result getSpaceList(@LoginUserAnnotation UserAddress userAddress,String type,String name) {
        return userSpaceService.getSpaceList(userAddress,type,name);
    }

    @GetMapping("getSpaceInfo")
    @LogAnnotation(operateType = "空间", operateContent = "获取空间信息")
    public Result getSpaceInfo(@LoginUserAnnotation UserAddress userAddress,Integer id) {
        return userSpaceService.getSpaceInfo(userAddress,id);
    }

    @GetMapping("getContractAddressInfo")
    @LogAnnotation(operateType = "空间", operateContent = "获取合约地址信息")
    public Result getContractAddressInfo(@LoginUserAnnotation UserAddress userAddress,String address) {
        return userSpaceService.getContractAddressInfo(address);
    }


}
