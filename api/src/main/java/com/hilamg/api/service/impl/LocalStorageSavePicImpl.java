package com.hilamg.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hilamg.api.service.UploadPicService;
import com.hilamg.common.constant.OneThinkerConfigConstant;
import com.hilamg.common.constant.ProjectConstant;
import com.hilamg.common.utils.UploadFileUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: yangyouqi
 * @Date: 2022/4/2 15:08
 * @Description:
 */
@Service
@Log4j2
class LocalStorageSavePicImpl implements UploadPicService {

    @Resource
    private OneThinkerConfigConstant oneThinkerConfigConstant;

    @Override
    public String uploadPic(MultipartFile multipartFile) {
        // 获取MultipartFile文件信息
        String uploadPath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<String> uploadPicNames = new ArrayList<>();
        try {
                // 获取上传文件目录
                String filePath = UploadFileUtils.getUploadPicName(multipartFile.getOriginalFilename(), uploadPath, oneThinkerConfigConstant.picConfig.localStorage.defaultPath);
                //校验是否存在文件信息，不存在则进行创建
                File file = UploadFileUtils.existsFilePath(oneThinkerConfigConstant.picConfig.localStorage.savePathConfig + filePath);
                // 写入图片信息
                FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
                uploadPicNames.add(filePath);
        } catch (Exception e) {
            log.error("本地文件上传异常，异常原因：" + e.getCause());
            throw new RuntimeException(e.getMessage());
        }
        // 返回相关上传图片信息
       return oneThinkerConfigConstant.picConfig.localStorage.host+uploadPicNames.get(0);
//        return UploadFileUtils.getSavePicBaseInfo(uploadPicNames, , ProjectConstant.PIC_SAVE_LOCALHOST);
    }
}
