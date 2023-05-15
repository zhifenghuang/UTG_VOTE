package com.hilamg.common.result;


/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500),//服务器内部错误

    TOKEN_TIME_OUT(322),//token失效
    PARAMETER_ERROR(403),
    BANNED_ERROR(405),//账号被封禁
    SIGN_ERROR(402),//签名错误

    VERSION(408); //需要提示版本更新
//    Parameter error



    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
