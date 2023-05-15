package com.ruoyi.utg.service;

import java.util.List;
import com.ruoyi.utg.domain.UserSpaceType;

/**
 * 空间类型Service接口
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public interface IUserSpaceTypeService 
{
    /**
     * 查询空间类型
     * 
     * @param id 空间类型ID
     * @return 空间类型
     */
    public UserSpaceType selectUserSpaceTypeById(Long id);

    /**
     * 查询空间类型列表
     * 
     * @param userSpaceType 空间类型
     * @return 空间类型集合
     */
    public List<UserSpaceType> selectUserSpaceTypeList(UserSpaceType userSpaceType);

    /**
     * 新增空间类型
     * 
     * @param userSpaceType 空间类型
     * @return 结果
     */
    public int insertUserSpaceType(UserSpaceType userSpaceType);

    /**
     * 修改空间类型
     * 
     * @param userSpaceType 空间类型
     * @return 结果
     */
    public int updateUserSpaceType(UserSpaceType userSpaceType);

    /**
     * 批量删除空间类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserSpaceTypeByIds(String ids);

    /**
     * 删除空间类型信息
     * 
     * @param id 空间类型ID
     * @return 结果
     */
    public int deleteUserSpaceTypeById(Long id);
}
