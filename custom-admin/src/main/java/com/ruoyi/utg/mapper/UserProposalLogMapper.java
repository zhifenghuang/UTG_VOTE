package com.ruoyi.utg.mapper;

import java.util.List;
import com.ruoyi.utg.domain.UserProposalLog;

/**
 * 投票记录Mapper接口
 * 
 * @author hh
 * @date 2022-05-31
 */
public interface UserProposalLogMapper 
{
    /**
     * 查询投票记录
     * 
     * @param id 投票记录ID
     * @return 投票记录
     */
    public UserProposalLog selectUserProposalLogById(Long id);

    /**
     * 查询投票记录列表
     * 
     * @param userProposalLog 投票记录
     * @return 投票记录集合
     */
    public List<UserProposalLog> selectUserProposalLogList(UserProposalLog userProposalLog);

    /**
     * 新增投票记录
     * 
     * @param userProposalLog 投票记录
     * @return 结果
     */
    public int insertUserProposalLog(UserProposalLog userProposalLog);

    /**
     * 修改投票记录
     * 
     * @param userProposalLog 投票记录
     * @return 结果
     */
    public int updateUserProposalLog(UserProposalLog userProposalLog);

    /**
     * 删除投票记录
     * 
     * @param id 投票记录ID
     * @return 结果
     */
    public int deleteUserProposalLogById(Long id);

    /**
     * 批量删除投票记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserProposalLogByIds(String[] ids);
}
