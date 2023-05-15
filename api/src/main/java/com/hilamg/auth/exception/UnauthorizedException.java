package com.hilamg.auth.exception;

import com.hilamg.base.exception.ServiceException;
import com.hilamg.common.result.ResultCode;

/**
 * 未认证异常
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public class UnauthorizedException extends ServiceException {
    public UnauthorizedException() {
        super("Unauthorized", ResultCode.UNAUTHORIZED.code());
    }
}
