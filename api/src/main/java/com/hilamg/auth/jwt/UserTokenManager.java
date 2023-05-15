package com.hilamg.auth.jwt;

import org.apache.commons.lang3.StringUtils;

/**
 * 维护用户token
 */
public class UserTokenManager {
    public static String generateToken(Integer id, String address) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(id, address);
    }

    public static Integer getUserId(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        JwtHelper jwtHelper = new JwtHelper();
        Integer userId = jwtHelper.verifyTokenAndGetUserId(token);
        if (userId == null || userId == 0) {
            return null;
        }
        return userId;
    }

    public static String getPhone(String token) {
        JwtHelper jwtHelper = new JwtHelper();
        String phone = jwtHelper.verifyTokenAndGetPhone(token);
        if (phone == null) {
            return null;
        }
        return phone;
    }

    public static void main(String[] args) {
        System.out.println(generateToken(1,"18329762647"));
    }
}
