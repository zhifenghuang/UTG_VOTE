package com.hilamg.auth.aop;

import com.hilamg.auth.exception.SignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Slf4j
@Component
@Aspect
public class AuthAop {

//    @Value("${spring.profiles.active}")
    private String env="dev";//当前激活的配置文件

    /**
     * 可偏移的时间(秒)
     */
    private static int window_size = 5;

    @Pointcut("@annotation(com.hilamg.common.annotations.AuthSignAnnotation)")
    public void mypointcut() {
    }

    @Before("mypointcut()")
    public void aroundTimes() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if (!this.checkSign(request)) {
            if (env.equals("prod")) {
                log.info("所有http接口鉴权通知");
                throw new SignException();
            }
        }
        String version = request.getHeader("version");
        String system = request.getHeader("system");
        log.info("app请求系统为:{}版本为:{}",system,version);
        //根据系统获取最新版本号，如果匹配则通过，如果不匹配则提示版本更新
//        HohoVersion didVersion = hohoVersionMapper.selectOne(new LambdaQueryWrapper<HohoVersion>()
//                .select(HohoVersion::getNumber)
//                .eq(HohoVersion::getType, system)
//                .orderByDesc(HohoVersion::getId)
//                .last("limit 1")
//        );
//        if (null == didVersion) {
//            throw new VersionException();
//        }
//        log.info("app当前版本为:{}",didVersion.getNumber());
//        if (!didVersion.getNumber().equals(version)) {
//            throw new VersionException();
//        }
    }


    private boolean checkSign(HttpServletRequest request) {
        //获得请求签名，如sign=19e907700db7ad91318424a97c54ed57
        String requestSign = request.getParameter("sign");
        if (StringUtils.isEmpty(requestSign)) {
            log.info("缺少签名");
            return false;
        }
        List<String> keys = new ArrayList<String>(request.getParameterMap().keySet());
        //排除sign参数
        keys.remove("sign");
        //排序
//        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        if(!keys.contains("timestamp")){
            return false;
        }
        for (String key : keys) {
            sb.append(key).append("=");
            //拼接字符串
            if (StringUtils.equals(key, "timestamp")) {
                // 时间偏移
                int requestTimestamp = Integer.valueOf(String.valueOf(request.getParameter(key)));
                int localTime = (int) (System.currentTimeMillis() / 1000);
                if (localTime - requestTimestamp > window_size) {
                    log.info("时间偏移");
                    return false;
                }
            }
            sb.append(request.getParameter(key) + "&");
        }
        String linkString = sb.toString();
        //去除最后一个'&'
        linkString = StringUtils.substring(linkString, 0, linkString.length() - 1);
        //密钥，自己修改
        String secret = "guolian";
        //混合密钥md5
        String sign = DigestUtils.md5Hex(linkString + secret);
        if (StringUtils.equals(sign, requestSign)) {
            return true;
        } else {
            log.info("鉴权失败");
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println((int) (System.currentTimeMillis() / 1000));
    }


}
