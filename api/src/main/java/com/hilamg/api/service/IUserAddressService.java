package com.hilamg.api.service;

import com.hilamg.api.dto.in.UpUserInfo;
import com.hilamg.api.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hilamg.common.result.Result;

import javax.validation.Valid;

/**
 * <p>
 * 用户地址信息 服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface IUserAddressService extends IService<UserAddress> {

    Result getToken(String adddres);

    Result updateUserInfo(UserAddress userAddress, UpUserInfo upUserInfo);

}
