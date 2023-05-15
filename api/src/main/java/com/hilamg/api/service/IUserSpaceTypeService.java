package com.hilamg.api.service;

import com.hilamg.api.dto.in.CreateSpace;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserSpaceType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 空间类型 服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface IUserSpaceTypeService extends IService<UserSpaceType> {

    List<UserSpaceType> getList(HttpServletRequest request);

}
