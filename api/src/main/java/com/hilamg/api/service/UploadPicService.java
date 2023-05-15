package com.hilamg.api.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yangyouqi
 * @Date: 2022/4/2 15:02
 * @Description: 上传图片service
 */
public interface UploadPicService {

    /**
     * 上传图片配置
     * @param request
     * @return
     */
    public String uploadPic(MultipartFile multipartFile);
}
