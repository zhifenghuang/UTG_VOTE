package com.ruoyi.utg.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.UserSpaceMapper;
import com.ruoyi.utg.domain.UserSpace;
import com.ruoyi.utg.service.IUserSpaceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户空间Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class UserSpaceServiceImpl implements IUserSpaceService 
{
    @Autowired
    private UserSpaceMapper userSpaceMapper;

    /**
     * 查询用户空间
     * 
     * @param id 用户空间ID
     * @return 用户空间
     */
    @Override
    public UserSpace selectUserSpaceById(Long id)
    {
        return userSpaceMapper.selectUserSpaceById(id);
    }

    /**
     * 查询用户空间列表
     * 
     * @param userSpace 用户空间
     * @return 用户空间
     */
    @Override
    public List<UserSpace> selectUserSpaceList(UserSpace userSpace)
    {
        return userSpaceMapper.selectUserSpaceList(userSpace);
    }

    /**
     * 新增用户空间
     * 
     * @param userSpace 用户空间
     * @return 结果
     */
    @Override
    public int insertUserSpace(UserSpace userSpace)
    {
        return userSpaceMapper.insertUserSpace(userSpace);
    }

    /**
     * 修改用户空间
     * 
     * @param userSpace 用户空间
     * @return 结果
     */
    @Override
    public int updateUserSpace(UserSpace userSpace)
    {
        userSpace.setUpdateDate(new Date());
        return userSpaceMapper.updateUserSpace(userSpace);
    }

    /**
     * 删除用户空间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserSpaceByIds(String ids)
    {
        return userSpaceMapper.deleteUserSpaceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户空间信息
     * 
     * @param id 用户空间ID
     * @return 结果
     */
    @Override
    public int deleteUserSpaceById(Long id)
    {
        return userSpaceMapper.deleteUserSpaceById(id);
    }
}
