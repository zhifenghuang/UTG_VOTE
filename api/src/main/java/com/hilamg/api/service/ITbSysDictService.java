package com.hilamg.api.service;

import com.hilamg.api.entity.TbSysDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-27
 */
public interface ITbSysDictService extends IService<TbSysDict> {

    String getSysDict(String key);

}
