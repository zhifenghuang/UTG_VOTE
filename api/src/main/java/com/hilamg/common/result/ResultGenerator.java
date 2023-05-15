package com.hilamg.common.result;

import com.hilamg.common.utils.i18n.I18nUtil;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS.code())
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> genSuccessResult(T data) {
        return new Result()
                .setCode(ResultCode.SUCCESS.code())
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        message = I18nUtil.getMessage(message);
        return new Result()
                .setCode(ResultCode.FAIL.code())
                .setMessage(message);
    }

    public static Result genUnauthorized(String message) {
        try {
            message = I18nUtil.getMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result()
                .setCode(ResultCode.UNAUTHORIZED.code())
                .setMessage(message);
    }

    public static Result genNotFound(String message) {
        try {
            message = I18nUtil.getMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result()
                .setCode(ResultCode.NOT_FOUND.code())
                .setMessage(message);
    }

    public static Result genInternalServerError(String message) {
        try {
            message = I18nUtil.getMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result()
                .setCode(ResultCode.FAIL.code())
                .setMessage(message);
    }

    public static Result genOthonerError(String message,int resultCode) {
        try {
            message = I18nUtil.getMessage(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result()
                .setCode(resultCode)
                .setMessage(message);
    }
}
