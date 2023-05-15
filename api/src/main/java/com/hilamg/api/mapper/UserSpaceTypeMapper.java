package com.hilamg.api.mapper;

import com.hilamg.api.entity.UserSpaceType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 空间类型 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Mapper
public interface UserSpaceTypeMapper extends BaseMapper<UserSpaceType> {

    List<UserSpaceType> getList(@Param("language") String language);

}
