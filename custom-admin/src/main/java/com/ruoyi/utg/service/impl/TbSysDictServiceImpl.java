package com.ruoyi.utg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.TbSysDictMapper;
import com.ruoyi.utg.domain.TbSysDict;
import com.ruoyi.utg.service.ITbSysDictService;
import com.ruoyi.common.core.text.Convert;

/**
 * 固定参数配置Service业务层处理
 * 
 * @author hh
 * @date 2022-06-24
 */
@Service
public class TbSysDictServiceImpl implements ITbSysDictService 
{
    @Autowired
    private TbSysDictMapper tbSysDictMapper;

    /**
     * 查询固定参数配置
     * 
     * @param id 固定参数配置ID
     * @return 固定参数配置
     */
    @Override
    public TbSysDict selectTbSysDictById(Long id)
    {
        return tbSysDictMapper.selectTbSysDictById(id);
    }

    /**
     * 查询固定参数配置列表
     * 
     * @param tbSysDict 固定参数配置
     * @return 固定参数配置
     */
    @Override
    public List<TbSysDict> selectTbSysDictList(TbSysDict tbSysDict)
    {
        return tbSysDictMapper.selectTbSysDictList(tbSysDict);
    }

    /**
     * 新增固定参数配置
     * 
     * @param tbSysDict 固定参数配置
     * @return 结果
     */
    @Override
    public int insertTbSysDict(TbSysDict tbSysDict)
    {
        return tbSysDictMapper.insertTbSysDict(tbSysDict);
    }

    /**
     * 修改固定参数配置
     * 
     * @param tbSysDict 固定参数配置
     * @return 结果
     */
    @Override
    public int updateTbSysDict(TbSysDict tbSysDict)
    {
        return tbSysDictMapper.updateTbSysDict(tbSysDict);
    }

    /**
     * 删除固定参数配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbSysDictByIds(String ids)
    {
        return tbSysDictMapper.deleteTbSysDictByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除固定参数配置信息
     * 
     * @param id 固定参数配置ID
     * @return 结果
     */
    @Override
    public int deleteTbSysDictById(Long id)
    {
        return tbSysDictMapper.deleteTbSysDictById(id);
    }
}
