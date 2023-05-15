package com.hilamg.api.service;

import com.hilamg.api.dto.in.CreateSpace;
import com.hilamg.api.dto.in.UpdateSpace;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserSpace;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;

/**
 * <p>
 * 用户空间 服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface IUserSpaceService extends IService<UserSpace> {

    Result createSpace(UserAddress userAddress, CreateSpace createSpace);

    Result updateSpace(UserAddress userAddress, UpdateSpace updateSpace);

    Result getSpaceList(UserAddress userAddress,String type,String name);

    Result getSpaceInfo(UserAddress userAddress,Integer id);

    Result getContractAddressInfo(String address);


}
