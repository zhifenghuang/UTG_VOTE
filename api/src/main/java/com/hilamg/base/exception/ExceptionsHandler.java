package com.hilamg.base.exception;

import com.hilamg.auth.exception.AuthException;
import com.hilamg.auth.exception.SignException;
import com.hilamg.auth.exception.UnauthorizedException;
import com.hilamg.auth.exception.VersionException;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import freemarker.core.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常接管
 *
 * @author Li Jinhui
 * @update 2019/1/7 09:22
 * @since 2018/12/6 10:00
 */
@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {

    /**
     * 注意：
     * 启用全局异常接管后，没有在此处定义拦截的异常都会默认返回500错误。
     * 若需要自定义拦截的异常，请在此处定义拦截。
     * 若需要输出异常的日志日志，请使用logger输出。
     */
    private final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);


    /**
     * 基本异常
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultGenerator.genInternalServerError("system-error");
    }

    /**
     * 请求路径无法找到异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result notFoundException() {
        return ResultGenerator.genNotFound("Not found");
    }

    /**
     * 请求参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, UnexpectedTypeException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public Result parameterException(Exception e) {
        BindException be = (BindException) e;
        FieldError error = be.getFieldError();
        return ResultGenerator.genOthonerError(error.getDefaultMessage(), 400);
    }

    /**
     * 上传文件过大异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result maxUploadSizeExceededException() {
        return ResultGenerator.genOthonerError("File is too large", 403);
    }

    /**
     * 服务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return ResultGenerator.genInternalServerError(e.getMessage());
    }


    /**
     * 认证异常
     */
    @ExceptionHandler(AuthException.class)
    public Result AuthException(AuthException e) {
        return ResultGenerator.genOthonerError(e.getMessage(), e.getCode());
    }

    /**
     * 鉴权异常
     */
    @ExceptionHandler(UnauthorizedException.class)
    public Result UnauthorizedException(UnauthorizedException e) {
        return ResultGenerator.genOthonerError(e.getMessage(), e.getCode());
    }

    /**
     * 版本更新异常
     */
    @ExceptionHandler(VersionException.class)
    public Result VersionException(VersionException e) {
        return ResultGenerator.genOthonerError(e.getMessage(), e.getCode());
    }

    /**
     * 签名认证错误
     */
    @ExceptionHandler(SignException.class)
    public Result SignException(SignException e) {
        return ResultGenerator.genOthonerError(e.getMessage(), e.getCode());
    }

//    @ExceptionHandler(BindException.class)
//    public Result BindException(BindException e) {
//        FieldError error = e.getFieldError();
//        return ResultGenerator.genInternalServerError(error.getDefaultMessage());
//    }

}