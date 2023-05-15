package com.hilamg.auth.config;

import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.IUserAddressService;
import com.hilamg.auth.exception.UnauthorizedException;
import com.hilamg.auth.jwt.UserTokenManager;
import com.hilamg.base.exception.ServiceException;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.constant.ProConst;
import com.hilamg.common.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Service
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String LOGIN_TOKEN_KEY = "token";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IUserAddressService userAddressService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserAddress.class) && parameter.hasParameterAnnotation(LoginUserAnnotation.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if (token == null || token.isEmpty()) {
            throw new UnauthorizedException();
        }
        Integer userId = UserTokenManager.getUserId(token);
        if (null == userId) {
            throw new UnauthorizedException();
        }
        Object object = redisUtil.hget("token", String.valueOf(userId));
        if (!token.equals(object)) {
            throw new UnauthorizedException();
        }
        UserAddress userAddress = userAddressService.getById(userId);
        if(null==userAddress){
            throw new UnauthorizedException();
        }
        if(ProConst.BannedEnum.DELETE.getValue().equals(userAddress.getAddress())){
            throw new ServiceException("ADDRESS_IS_BAN");
        }
        return userAddress;
    }
}
