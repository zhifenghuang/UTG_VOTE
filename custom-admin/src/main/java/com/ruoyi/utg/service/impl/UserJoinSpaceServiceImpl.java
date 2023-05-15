package com.ruoyi.utg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.UserJoinSpaceMapper;
import com.ruoyi.utg.domain.UserJoinSpace;
import com.ruoyi.utg.service.IUserJoinSpaceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户加入空间列Service业务层处理
 * 
 * @author hh
 * @date 2022-05-31
 */
@Service
public class UserJoinSpaceServiceImpl implements IUserJoinSpaceService 
{
    @Autowired
    private UserJoinSpaceMapper userJoinSpaceMapper;

    /**
     * 查询用户加入空间列
     * 
     * @param id 用户加入空间列ID
     * @return 用户加入空间列
     */
    @Override
    public UserJoinSpace selectUserJoinSpaceById(Long id)
    {
        return userJoinSpaceMapper.selectUserJoinSpaceById(id);
    }

    /**
     * 查询用户加入空间列列表
     * 
     * @param userJoinSpace 用户加入空间列
     * @return 用户加入空间列
     */
    @Override
    public List<UserJoinSpace> selectUserJoinSpaceList(UserJoinSpace userJoinSpace)
    {
        return userJoinSpaceMapper.selectUserJoinSpaceList(userJoinSpace);
    }

    /**
     * 新增用户加入空间列
     * 
     * @param userJoinSpace 用户加入空间列
     * @return 结果
     */
    @Override
    public int insertUserJoinSpace(UserJoinSpace userJoinSpace)
    {
        return userJoinSpaceMapper.insertUserJoinSpace(userJoinSpace);
    }

    /**
     * 修改用户加入空间列
     * 
     * @param userJoinSpace 用户加入空间列
     * @return 结果
     */
    @Override
    public int updateUserJoinSpace(UserJoinSpace userJoinSpace)
    {
        return userJoinSpaceMapper.updateUserJoinSpace(userJoinSpace);
    }

    /**
     * 删除用户加入空间列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserJoinSpaceByIds(String ids)
    {
        return userJoinSpaceMapper.deleteUserJoinSpaceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户加入空间列信息
     * 
     * @param id 用户加入空间列ID
     * @return 结果
     */
    @Override
    public int deleteUserJoinSpaceById(Long id)
    {
        return userJoinSpaceMapper.deleteUserJoinSpaceById(id);
    }
}
