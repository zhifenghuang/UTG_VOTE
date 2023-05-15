package com.hilamg.api.mapper;

import com.hilamg.api.entity.TbSysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-27
 */
public interface TbSysDictMapper extends BaseMapper<TbSysDict> {

    String getValueByKey(String key);

    List<TbSysDict> getValueByLike(String key);

}
