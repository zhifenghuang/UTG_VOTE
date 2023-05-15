package com.hilamg.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hilamg.api.entity.TbSysDict;
import com.hilamg.api.mapper.TbSysDictMapper;
import com.hilamg.api.service.ITbSysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-27
 */
@Service
public class TbSysDictServiceImpl extends ServiceImpl<TbSysDictMapper, TbSysDict> implements ITbSysDictService {

    @Autowired
    TbSysDictMapper dictMapper;

    @Override
    public String getSysDict(String key) {
        return dictMapper.getValueByKey(key);
    }
}
