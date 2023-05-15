package com.ruoyi.utg.mapper;

import java.util.List;
import com.ruoyi.utg.domain.UserSpace;

/**
 * 用户空间Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public interface UserSpaceMapper 
{
    /**
     * 查询用户空间
     * 
     * @param id 用户空间ID
     * @return 用户空间
     */
    public UserSpace selectUserSpaceById(Long id);

    /**
     * 查询用户空间列表
     * 
     * @param userSpace 用户空间
     * @return 用户空间集合
     */
    public List<UserSpace> selectUserSpaceList(UserSpace userSpace);

    /**
     * 新增用户空间
     * 
     * @param userSpace 用户空间
     * @return 结果
     */
    public int insertUserSpace(UserSpace userSpace);

    /**
     * 修改用户空间
     * 
     * @param userSpace 用户空间
     * @return 结果
     */
    public int updateUserSpace(UserSpace userSpace);

    /**
     * 删除用户空间
     * 
     * @param id 用户空间ID
     * @return 结果
     */
    public int deleteUserSpaceById(Long id);

    /**
     * 批量删除用户空间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserSpaceByIds(String[] ids);
}
