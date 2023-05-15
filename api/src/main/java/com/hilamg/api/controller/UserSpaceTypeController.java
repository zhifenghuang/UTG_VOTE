package com.hilamg.api.controller;


import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.IUserSpaceTypeService;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 空间类型 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/api/userSpaceType")
public class UserSpaceTypeController {

    @Autowired
    IUserSpaceTypeService userSpaceTypeService;

    @GetMapping("")
    @LogAnnotation(operateType = "空间类型", operateContent = "获取空间类型列表")
    public Result getList(HttpServletRequest request) {
        return ResultGenerator.genSuccessResult(userSpaceTypeService.getList(request));
    }

}
