package com.hilamg.auth.exception;

import com.hilamg.base.exception.ServiceException;
import com.hilamg.common.result.ResultCode;

/**
 * 认证异常
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public class SignException extends ServiceException {
    public SignException() {
        super("Sign-Error", ResultCode.SIGN_ERROR.code());
    }
}
