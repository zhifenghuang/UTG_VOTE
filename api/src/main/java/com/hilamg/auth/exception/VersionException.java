package com.hilamg.auth.exception;

import com.hilamg.base.exception.ServiceException;
import com.hilamg.common.result.ResultCode;

/**
 * 认证异常
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public class VersionException extends ServiceException {
    public VersionException() {
        super("Version-Exception", ResultCode.VERSION.code());
    }
}
