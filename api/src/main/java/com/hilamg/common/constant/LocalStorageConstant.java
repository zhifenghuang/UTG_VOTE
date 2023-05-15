package com.hilamg.common.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyouqi
 * @Date: 2022/4/7 15:09
 * @Description:
 */
@Getter
@Setter
@Component
public class LocalStorageConstant {
    /**
     * 访问地址
     */
    public String host;
    /**
     * 默认图片上传路径
     */
    public String savePathConfig;
    /**
     * 上传默认目录
     */
    public String defaultPath;
}
