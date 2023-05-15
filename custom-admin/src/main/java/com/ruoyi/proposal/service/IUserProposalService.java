package com.ruoyi.proposal.service;

import java.util.List;
import com.ruoyi.proposal.domain.UserProposal;

/**
 * 提案列Service接口
 * 
 * @author hh
 * @date 2022-05-31
 */
public interface IUserProposalService 
{
    /**
     * 查询提案列
     * 
     * @param id 提案列ID
     * @return 提案列
     */
    public UserProposal selectUserProposalById(Long id);

    /**
     * 查询提案列列表
     * 
     * @param userProposal 提案列
     * @return 提案列集合
     */
    public List<UserProposal> selectUserProposalList(UserProposal userProposal);

    /**
     * 新增提案列
     * 
     * @param userProposal 提案列
     * @return 结果
     */
    public int insertUserProposal(UserProposal userProposal);

    /**
     * 修改提案列
     * 
     * @param userProposal 提案列
     * @return 结果
     */
    public int updateUserProposal(UserProposal userProposal);

    /**
     * 批量删除提案列
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserProposalByIds(String ids);

    /**
     * 删除提案列信息
     * 
     * @param id 提案列ID
     * @return 结果
     */
    public int deleteUserProposalById(Long id);
}
