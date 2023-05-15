package com.hilamg.api.dto.in;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpUserInfo {

    @NotBlank(message = "NIKENAME_IS_NULL")
    private String nikeName;

    @NotBlank(message = "USERINFO_IS_NULL")
    private String userInfo;
}
