package com.hilamg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@MapperScan("com.hilamg.api.mapper")
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class UTGApplication {
    public static void main(String[] args) {
        SpringApplication.run(UTGApplication.class, args);
    }

}
