package com.hilamg.base.exception;

/**
 * Token异常
 *
 * @author Li Jinhui
 * @since 2018/12/7
 */
public class TokenException extends RuntimeException {
    public TokenException() {
        super("False token");
    }
}
