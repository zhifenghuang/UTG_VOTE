package com.ruoyi.proposal.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.proposal.mapper.UserProposalMapper;
import com.ruoyi.proposal.domain.UserProposal;
import com.ruoyi.proposal.service.IUserProposalService;
import com.ruoyi.common.core.text.Convert;

/**
 * 提案列Service业务层处理
 * 
 * @author hh
 * @date 2022-05-31
 */
@Service
public class UserProposalServiceImpl implements IUserProposalService 
{
    @Autowired
    private UserProposalMapper userProposalMapper;

    /**
     * 查询提案列
     * 
     * @param id 提案列ID
     * @return 提案列
     */
    @Override
    public UserProposal selectUserProposalById(Long id)
    {
        return userProposalMapper.selectUserProposalById(id);
    }

    /**
     * 查询提案列列表
     * 
     * @param userProposal 提案列
     * @return 提案列
     */
    @Override
    public List<UserProposal> selectUserProposalList(UserProposal userProposal)
    {
        return userProposalMapper.selectUserProposalList(userProposal);
    }

    /**
     * 新增提案列
     * 
     * @param userProposal 提案列
     * @return 结果
     */
    @Override
    public int insertUserProposal(UserProposal userProposal)
    {
        return userProposalMapper.insertUserProposal(userProposal);
    }

    /**
     * 修改提案列
     * 
     * @param userProposal 提案列
     * @return 结果
     */
    @Override
    public int updateUserProposal(UserProposal userProposal)
    {
        return 1;
    }

    /**
     * 删除提案列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserProposalByIds(String ids)
    {
        return userProposalMapper.deleteUserProposalByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提案列信息
     * 
     * @param id 提案列ID
     * @return 结果
     */
    @Override
    public int deleteUserProposalById(Long id)
    {
        return userProposalMapper.deleteUserProposalById(id);
    }
}
