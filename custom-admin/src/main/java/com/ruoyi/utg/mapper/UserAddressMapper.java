package com.ruoyi.utg.mapper;

import java.util.List;
import com.ruoyi.utg.domain.UserAddress;

/**
 * 用户地址信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public interface UserAddressMapper 
{
    /**
     * 查询用户地址信息
     * 
     * @param id 用户地址信息ID
     * @return 用户地址信息
     */
    public UserAddress selectUserAddressById(Long id);

    /**
     * 查询用户地址信息列表
     * 
     * @param userAddress 用户地址信息
     * @return 用户地址信息集合
     */
    public List<UserAddress> selectUserAddressList(UserAddress userAddress);

    /**
     * 新增用户地址信息
     * 
     * @param userAddress 用户地址信息
     * @return 结果
     */
    public int insertUserAddress(UserAddress userAddress);

    /**
     * 修改用户地址信息
     * 
     * @param userAddress 用户地址信息
     * @return 结果
     */
    public int updateUserAddress(UserAddress userAddress);

    /**
     * 删除用户地址信息
     * 
     * @param id 用户地址信息ID
     * @return 结果
     */
    public int deleteUserAddressById(Long id);

    /**
     * 批量删除用户地址信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserAddressByIds(String[] ids);
}
