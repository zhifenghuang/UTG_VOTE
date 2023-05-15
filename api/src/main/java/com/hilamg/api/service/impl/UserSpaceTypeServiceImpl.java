package com.hilamg.api.service.impl;

import com.hilamg.api.entity.UserJoinSpace;
import com.hilamg.api.entity.UserSpaceType;
import com.hilamg.api.mapper.UserJoinSpaceMapper;
import com.hilamg.api.mapper.UserSpaceTypeMapper;
import com.hilamg.api.service.IUserSpaceTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 空间类型 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Service
public class UserSpaceTypeServiceImpl extends ServiceImpl<UserSpaceTypeMapper, UserSpaceType> implements IUserSpaceTypeService {

    @Autowired
    UserSpaceTypeMapper userSpaceTypeMapper;

    @Override
    public List<UserSpaceType> getList(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if (StringUtils.isBlank(language)) {
            language = "zh_CN";
        }
        return userSpaceTypeMapper.getList(language);
    }
}
