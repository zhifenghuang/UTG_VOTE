package com.hilamg.api.service;

import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserJoinSpace;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;

/**
 * <p>
 * 用户加入空间列表 服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface IUserJoinSpaceService extends IService<UserJoinSpace> {

    Result joinSpace(UserAddress userAddress, Integer  spaceId);

    Result exitSpace(UserAddress userAddress, Integer  spaceId);

    Result userSpaceList(UserAddress userAddress);

}
