package com.hilamg.api.controller;

import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.UploadPicService;
import com.hilamg.api.service.impl.UploadService;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    UploadPicService uploadPicService;

    @PostMapping("")
    @LogAnnotation(operateType = "图片", operateContent = "上传图片")
    public Result upload(MultipartFile file) {
        return ResultGenerator.genSuccessResult(uploadPicService.uploadPic(file));
    }
}
