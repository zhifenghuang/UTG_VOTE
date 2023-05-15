package com.ruoyi.utg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.UserAddressMapper;
import com.ruoyi.utg.domain.UserAddress;
import com.ruoyi.utg.service.IUserAddressService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户地址信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class UserAddressServiceImpl implements IUserAddressService 
{
    @Autowired
    private UserAddressMapper userAddressMapper;

    /**
     * 查询用户地址信息
     * 
     * @param id 用户地址信息ID
     * @return 用户地址信息
     */
    @Override
    public UserAddress selectUserAddressById(Long id)
    {
        return userAddressMapper.selectUserAddressById(id);
    }

    /**
     * 查询用户地址信息列表
     * 
     * @param userAddress 用户地址信息
     * @return 用户地址信息
     */
    @Override
    public List<UserAddress> selectUserAddressList(UserAddress userAddress)
    {
        return userAddressMapper.selectUserAddressList(userAddress);
    }

    /**
     * 新增用户地址信息
     * 
     * @param userAddress 用户地址信息
     * @return 结果
     */
    @Override
    public int insertUserAddress(UserAddress userAddress)
    {
        return userAddressMapper.insertUserAddress(userAddress);
    }

    /**
     * 修改用户地址信息
     * 
     * @param userAddress 用户地址信息
     * @return 结果
     */
    @Override
    public int updateUserAddress(UserAddress userAddress)
    {
        return userAddressMapper.updateUserAddress(userAddress);
    }

    /**
     * 删除用户地址信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserAddressByIds(String ids)
    {
        return userAddressMapper.deleteUserAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户地址信息信息
     * 
     * @param id 用户地址信息ID
     * @return 结果
     */
    @Override
    public int deleteUserAddressById(Long id)
    {
        return userAddressMapper.deleteUserAddressById(id);
    }
}
