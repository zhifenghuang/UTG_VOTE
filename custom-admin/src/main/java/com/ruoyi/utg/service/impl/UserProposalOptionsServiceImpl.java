package com.ruoyi.utg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.UserProposalOptionsMapper;
import com.ruoyi.utg.domain.UserProposalOptions;
import com.ruoyi.utg.service.IUserProposalOptionsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 提案投票选项Service业务层处理
 * 
 * @author hh
 * @date 2022-05-31
 */
@Service
public class UserProposalOptionsServiceImpl implements IUserProposalOptionsService 
{
    @Autowired
    private UserProposalOptionsMapper userProposalOptionsMapper;

    /**
     * 查询提案投票选项
     * 
     * @param id 提案投票选项ID
     * @return 提案投票选项
     */
    @Override
    public UserProposalOptions selectUserProposalOptionsById(Long id)
    {
        return userProposalOptionsMapper.selectUserProposalOptionsById(id);
    }

    /**
     * 查询提案投票选项列表
     * 
     * @param userProposalOptions 提案投票选项
     * @return 提案投票选项
     */
    @Override
    public List<UserProposalOptions> selectUserProposalOptionsList(UserProposalOptions userProposalOptions)
    {
        return userProposalOptionsMapper.selectUserProposalOptionsList(userProposalOptions);
    }

    /**
     * 新增提案投票选项
     * 
     * @param userProposalOptions 提案投票选项
     * @return 结果
     */
    @Override
    public int insertUserProposalOptions(UserProposalOptions userProposalOptions)
    {
        return userProposalOptionsMapper.insertUserProposalOptions(userProposalOptions);
    }

    /**
     * 修改提案投票选项
     * 
     * @param userProposalOptions 提案投票选项
     * @return 结果
     */
    @Override
    public int updateUserProposalOptions(UserProposalOptions userProposalOptions)
    {
        return userProposalOptionsMapper.updateUserProposalOptions(userProposalOptions);
    }

    /**
     * 删除提案投票选项对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserProposalOptionsByIds(String ids)
    {
        return userProposalOptionsMapper.deleteUserProposalOptionsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提案投票选项信息
     * 
     * @param id 提案投票选项ID
     * @return 结果
     */
    @Override
    public int deleteUserProposalOptionsById(Long id)
    {
        return userProposalOptionsMapper.deleteUserProposalOptionsById(id);
    }
}
