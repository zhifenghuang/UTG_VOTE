package com.ruoyi.utg.mapper;

import java.util.List;
import com.ruoyi.utg.domain.UserJoinSpace;

/**
 * 用户加入空间列Mapper接口
 * 
 * @author hh
 * @date 2022-05-31
 */
public interface UserJoinSpaceMapper 
{
    /**
     * 查询用户加入空间列
     * 
     * @param id 用户加入空间列ID
     * @return 用户加入空间列
     */
    public UserJoinSpace selectUserJoinSpaceById(Long id);

    /**
     * 查询用户加入空间列列表
     * 
     * @param userJoinSpace 用户加入空间列
     * @return 用户加入空间列集合
     */
    public List<UserJoinSpace> selectUserJoinSpaceList(UserJoinSpace userJoinSpace);

    /**
     * 新增用户加入空间列
     * 
     * @param userJoinSpace 用户加入空间列
     * @return 结果
     */
    public int insertUserJoinSpace(UserJoinSpace userJoinSpace);

    /**
     * 修改用户加入空间列
     * 
     * @param userJoinSpace 用户加入空间列
     * @return 结果
     */
    public int updateUserJoinSpace(UserJoinSpace userJoinSpace);

    /**
     * 删除用户加入空间列
     * 
     * @param id 用户加入空间列ID
     * @return 结果
     */
    public int deleteUserJoinSpaceById(Long id);

    /**
     * 批量删除用户加入空间列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserJoinSpaceByIds(String[] ids);
}
