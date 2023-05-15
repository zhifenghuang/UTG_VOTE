package com.hilamg.auth.aop;

import com.alibaba.fastjson.JSON;
import com.hilamg.auth.jwt.UserTokenManager;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.result.Result;
import com.hilamg.common.utils.AddressUtils;
import com.hilamg.common.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author Administrator
 */
@Slf4j
@Component
@Aspect
public class LogAop {

    @Pointcut("@annotation(com.hilamg.common.annotations.LogAnnotation)")
    public void mypointcut() {
    }

    @Around("mypointcut()")
    public Object aroundTimes(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // 定义操作方式
        String operatetype = "";
        // 定义操作内容
        String operatecontent = "";
        // 获取注解中的操作方式
        if (method != null && !"".equals(method)) {
            // 获取自定义注解操作
            LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
            // 获取用户操作方式
            operatetype = logAnnotation.operateType();
            log.debug("接口的操作类型==:" + operatetype);
            // 获取用户操作内容
            operatecontent = logAnnotation.operateContent();
            log.debug("接口的操作详情==:" + operatecontent);
        }
        // 获取请求的类名
        String classname = joinPoint.getTarget().getClass().getName();
        // 获取请求的方法名
        // 获取请求的ip地址
        String ip = IpUtils.getIpAddress(request);
        // 获取userId
        String token = request.getHeader("token");
        Integer userId = UserTokenManager.getUserId(token);
        String detail = JSON.toJSONString(request.getParameterMap());
        String address = AddressUtils.getRealAddressByIP(ip);
        if (null != userId) {
            log.info("用户:{}进行操作{}，请求接口：{}，请求地址：{}，请求参数：{}",
                    userId, operatetype + "-" + operatecontent, request.getRequestURI(), address, detail);
        } else {
            log.info("无鉴权接口{}进行操作{}，，请求地址：{}，请求参数：{}",
                    request.getRequestURI(), operatetype + "-" + operatecontent, address, detail);
        }
        //开始计算时间
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - beginTime;
        if (elapsedTime < 500) {
            log.info(operatetype + "-" + operatecontent + ", 接口响应时间为:" + elapsedTime + "ms.");
        } else {
            log.warn(operatetype + "-" + operatecontent + ", 接口响应时间为:" + elapsedTime + "ms.");
        }
//        // 获取操作状态
//        Result statusMap = (Result) result;
//        Integer code = statusMap.getCode();
//        String resultInfo = statusMap.getMessage();
//        Object data = statusMap.getData();
//        String status;
//        if (code == 0) {
//            status = "操作成功";
//        } else {
//            status = "操作失败";
//        }
        return result;
    }
}
