package com.hilamg.api.service.impl;

import com.hilamg.common.result.Result;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UploadService {

    public String upload(MultipartFile file) throws IOException {
        String fileName=String.valueOf(System.currentTimeMillis());
        String directory = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        FileOutputStream fop = null;
        try {
            InputStream inputStream = file.getInputStream();
            byte[] bytes = IOUtils.toByteArray(inputStream);
            String uploadPath = "";
            //constructs upload file path
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                uploadPath = System.getProperty("user.dir") + File.separator + "webapp"
                        + File.separator + "common/upload" + File.separator + directory;
            } else {
                //linux服务器上传  //System.getProperty("user.dir")+ File.separator+
                uploadPath = "/web/upload" + File.separator + directory;
            }
            File file2 = new File(uploadPath);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            String fileTrueName = uploadPath + File.separator + fileName;
            File file3 = new File(fileTrueName);
            if (!file3.exists()) {
                file3.createNewFile();
            }
            fop = new FileOutputStream(file3);
            FileUtils.writeByteArrayToFile(file3, file.getBytes());
            fop.flush();
            fop.close();
            return "upload" + File.separator + directory + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fop.close();
        }
        return null;
    }

}
