package com.hilamg.api.mapper;

import com.hilamg.api.dto.out.SpaceList;
import com.hilamg.api.entity.UserJoinSpace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户加入空间列表 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface UserJoinSpaceMapper extends BaseMapper<UserJoinSpace> {

    List<SpaceList> getUserSpaceList(Integer userId);

}
