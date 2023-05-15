package com.hilamg.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hilamg.api.dto.in.UpUserInfo;
import com.hilamg.api.dto.out.UserInfo;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.mapper.UserAddressMapper;
import com.hilamg.api.service.IUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hilamg.auth.jwt.UserTokenManager;
import com.hilamg.common.redis.RedisUtil;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import com.hilamg.common.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户地址信息 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements IUserAddressService {


    @Autowired
    UserAddressMapper userAddressMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Result getToken(String adddres) {
        if (StringUtils.isBlank(adddres)) {
            return ResultGenerator.genFailResult("ADDRESS_IS_NULL");
        }
        String profex=adddres.substring(0,2);
        if(profex.equals("ux") ||profex.equals("Ux")){
            adddres="0x"+adddres.substring(2,adddres.length());
        }
        UserAddress userAddress = userAddressMapper.selectOne(new LambdaQueryWrapper<UserAddress>()
                .eq(UserAddress::getAddress, adddres).last("limit 1"));
        if (null == userAddress) {
            userAddress = new UserAddress();
            userAddress.setAddress(adddres).setCreateDate(new Date());
            userAddressMapper.insert(userAddress);
        }
        String token = UserTokenManager.generateToken(userAddress.getId(), userAddress.getAddress());
        redisUtil.hset("token",userAddress.getId().toString(),token);
        Map<String, String> info = new HashMap<String, String>();
        info.put("token", token);
        return ResultGenerator.genSuccessResult(info);
    }

    @Override
    public Result updateUserInfo(UserAddress userAddress, UpUserInfo upUserInfo) {
        BeanUtils.copyProperties(upUserInfo,userAddress);
        userAddressMapper.updateById(userAddress);
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(userAddress,userInfo);
        return ResultGenerator.genSuccessResult(userInfo);
    }
}
