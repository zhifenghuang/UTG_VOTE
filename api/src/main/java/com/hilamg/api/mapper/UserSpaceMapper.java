package com.hilamg.api.mapper;

import com.hilamg.api.dto.out.SpaceList;
import com.hilamg.api.entity.UserSpace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户空间 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface UserSpaceMapper extends BaseMapper<UserSpace> {

    List<SpaceList> getSpaceList(@Param("type") String type,@Param("name") String name);

}
