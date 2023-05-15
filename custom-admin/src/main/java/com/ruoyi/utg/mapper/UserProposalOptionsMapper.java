package com.ruoyi.utg.mapper;

import java.util.List;
import com.ruoyi.utg.domain.UserProposalOptions;

/**
 * 提案投票选项Mapper接口
 * 
 * @author hh
 * @date 2022-05-31
 */
public interface UserProposalOptionsMapper 
{
    /**
     * 查询提案投票选项
     * 
     * @param id 提案投票选项ID
     * @return 提案投票选项
     */
    public UserProposalOptions selectUserProposalOptionsById(Long id);

    /**
     * 查询提案投票选项列表
     * 
     * @param userProposalOptions 提案投票选项
     * @return 提案投票选项集合
     */
    public List<UserProposalOptions> selectUserProposalOptionsList(UserProposalOptions userProposalOptions);

    /**
     * 新增提案投票选项
     * 
     * @param userProposalOptions 提案投票选项
     * @return 结果
     */
    public int insertUserProposalOptions(UserProposalOptions userProposalOptions);

    /**
     * 修改提案投票选项
     * 
     * @param userProposalOptions 提案投票选项
     * @return 结果
     */
    public int updateUserProposalOptions(UserProposalOptions userProposalOptions);

    /**
     * 删除提案投票选项
     * 
     * @param id 提案投票选项ID
     * @return 结果
     */
    public int deleteUserProposalOptionsById(Long id);

    /**
     * 批量删除提案投票选项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserProposalOptionsByIds(String[] ids);
}
