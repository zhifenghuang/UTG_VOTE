package com.ruoyi.utg.service;

import java.util.List;
import com.ruoyi.utg.domain.TbSysDict;

/**
 * 固定参数配置Service接口
 * 
 * @author hh
 * @date 2022-06-24
 */
public interface ITbSysDictService 
{
    /**
     * 查询固定参数配置
     * 
     * @param id 固定参数配置ID
     * @return 固定参数配置
     */
    public TbSysDict selectTbSysDictById(Long id);

    /**
     * 查询固定参数配置列表
     * 
     * @param tbSysDict 固定参数配置
     * @return 固定参数配置集合
     */
    public List<TbSysDict> selectTbSysDictList(TbSysDict tbSysDict);

    /**
     * 新增固定参数配置
     * 
     * @param tbSysDict 固定参数配置
     * @return 结果
     */
    public int insertTbSysDict(TbSysDict tbSysDict);

    /**
     * 修改固定参数配置
     * 
     * @param tbSysDict 固定参数配置
     * @return 结果
     */
    public int updateTbSysDict(TbSysDict tbSysDict);

    /**
     * 批量删除固定参数配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbSysDictByIds(String ids);

    /**
     * 删除固定参数配置信息
     * 
     * @param id 固定参数配置ID
     * @return 结果
     */
    public int deleteTbSysDictById(Long id);
}
