package com.hilamg.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: yangyouqi
 * @Date: 2022/4/3 11:11
 * @Description: 文件名统一生成
 */
@Component
@Log4j2
public class UploadFileUtils {

    /**
     * 从form-data中获取上传文件内容
     * @param request
     * @return
     */
    public static MultipartFile getMultipartFile(HttpServletRequest request) {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        MultipartFile file = mhs.getFile("file");

        if (ObjectUtils.isEmpty(file)) {
            throw new RuntimeException("上传文件为空");
        }

        if (ObjectUtils.isEmpty(file.getOriginalFilename()) || ObjectUtils.isEmpty(file.getOriginalFilename().split("\\.")) || file.getOriginalFilename().split("\\.").length == 0) {
            throw new RuntimeException("文件信息异常");
        }
        return file;
    }

    /**
     * 从form-data中获取全部上传文件内容
     */
    public static List<MultipartFile> getMultipartFiles(HttpServletRequest request) {
        MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;

        List<MultipartFile> multipartFiles = new ArrayList<>();

        for (String mapKey : mhs.getMultiFileMap().keySet()) {
            multipartFiles.addAll(mhs.getMultiFileMap().get(mapKey));
        }

        if (ObjectUtils.isEmpty(multipartFiles) || multipartFiles.size() == 0) {
            throw new RuntimeException("上传文件为空");
        }

        return multipartFiles;
    }


    /**
     * 统一生成文件名： 格式 yyyyMMddHHmmss + “_” + 随机数 + 后缀名
     * @param originalFilename
     * @return
     */
    public static String createFileName(String originalFilename) {

        if (ObjectUtils.isEmpty(originalFilename)) {
            throw new RuntimeException("文件名为空");
        }

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = bartDateFormat.format(new Date());

        return newFileName + "_" + Math.round(Math.random()*1000)  +  suffix;
    }


    /**
     * 校验文件夹是否存在，如果不存在则进行创建目录信息，最后再返回文件信息
     * @param filePath
     * @return
     */
    public static File existsFilePath(String filePath) {
        // 截取文件名
        String fileName = "";

        if (filePath.contains(".")) {
            fileName = filePath.substring(filePath.lastIndexOf("/"));
            filePath = filePath.replace(fileName,"");
        }

        //先检查是否有文件夹
        File fileTemp = new File(filePath);
        //没有则创建
        if (!fileTemp.exists() && !fileTemp.isDirectory()) {
            log.info("创建文件：" + filePath);
            fileTemp.mkdirs();
        }

        if (ObjectUtils.isEmpty(fileName)) {
            return fileTemp;
        } else {
            return new File(filePath + fileName);
        }

    }


    /**
     * 获取上传图片名
     * @param originalFilename
     * @param uploadPath
     * @return
     */
    public static String getUploadPicName(String originalFilename, String uploadPath,String defaultPath) {

        String key = UploadFileUtils.createFileName(originalFilename);

        if (ObjectUtils.isEmpty(uploadPath)) {
            key = defaultPath + "/" + key;
        } else {
            key = uploadPath + "/" + key;
        }
        return key;
    }

    /**
     * multipartFile转成File文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static File getPicTempFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileNamePath = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        // 后缀名
        String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
        // multipartFile转成File文件
        File localFile = File.createTempFile(fileNamePath, suffixName);
        // 写入文件信息
        multipartFile.transferTo(localFile);
        return localFile;
    }


    /**
     * 返回存储信息
     * @param uploadPath：上传存储地址
     * @param host：图片地址
     * @param originType：来源
     * @return
     */
    public static JSONObject getSavePicBaseInfo(List<String> uploadPath,String host,String originType) {
        JSONObject picObj = new JSONObject();
        picObj.put("path",uploadPath);
        picObj.put("host",host);
        picObj.put("originType",originType);
        return picObj;
    }
}
