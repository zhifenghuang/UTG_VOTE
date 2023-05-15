package com.ruoyi.utg.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.UserSpaceTypeMapper;
import com.ruoyi.utg.domain.UserSpaceType;
import com.ruoyi.utg.service.IUserSpaceTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 空间类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class UserSpaceTypeServiceImpl implements IUserSpaceTypeService 
{
    @Autowired
    private UserSpaceTypeMapper userSpaceTypeMapper;

    /**
     * 查询空间类型
     * 
     * @param id 空间类型ID
     * @return 空间类型
     */
    @Override
    public UserSpaceType selectUserSpaceTypeById(Long id)
    {
        return userSpaceTypeMapper.selectUserSpaceTypeById(id);
    }

    /**
     * 查询空间类型列表
     * 
     * @param userSpaceType 空间类型
     * @return 空间类型
     */
    @Override
    public List<UserSpaceType> selectUserSpaceTypeList(UserSpaceType userSpaceType)
    {
        return userSpaceTypeMapper.selectUserSpaceTypeList(userSpaceType);
    }

    /**
     * 新增空间类型
     * 
     * @param userSpaceType 空间类型
     * @return 结果
     */
    @Override
    public int insertUserSpaceType(UserSpaceType userSpaceType)
    {
        return userSpaceTypeMapper.insertUserSpaceType(userSpaceType);
    }

    /**
     * 修改空间类型
     * 
     * @param userSpaceType 空间类型
     * @return 结果
     */
    @Override
    public int updateUserSpaceType(UserSpaceType userSpaceType)
    {
        userSpaceType.setUpdateDate(new Date());
        return userSpaceTypeMapper.updateUserSpaceType(userSpaceType);
    }

    /**
     * 删除空间类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserSpaceTypeByIds(String ids)
    {
        return userSpaceTypeMapper.deleteUserSpaceTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除空间类型信息
     * 
     * @param id 空间类型ID
     * @return 结果
     */
    @Override
    public int deleteUserSpaceTypeById(Long id)
    {
        return userSpaceTypeMapper.deleteUserSpaceTypeById(id);
    }
}
