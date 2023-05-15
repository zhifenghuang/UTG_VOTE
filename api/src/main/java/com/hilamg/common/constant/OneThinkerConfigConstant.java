package com.hilamg.common.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: yangyouqi
 * @Date: 2022/4/7 15:04
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "one-thinker-config")
@Getter
@Setter
public class OneThinkerConfigConstant {
    public PicConfigConstant picConfig;
}
