package com.ruoyi.utg.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.utg.mapper.UserProposalLogMapper;
import com.ruoyi.utg.domain.UserProposalLog;
import com.ruoyi.utg.service.IUserProposalLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 投票记录Service业务层处理
 * 
 * @author hh
 * @date 2022-05-31
 */
@Service
public class UserProposalLogServiceImpl implements IUserProposalLogService 
{
    @Autowired
    private UserProposalLogMapper userProposalLogMapper;

    /**
     * 查询投票记录
     * 
     * @param id 投票记录ID
     * @return 投票记录
     */
    @Override
    public UserProposalLog selectUserProposalLogById(Long id)
    {
        return userProposalLogMapper.selectUserProposalLogById(id);
    }

    /**
     * 查询投票记录列表
     * 
     * @param userProposalLog 投票记录
     * @return 投票记录
     */
    @Override
    public List<UserProposalLog> selectUserProposalLogList(UserProposalLog userProposalLog)
    {
        return userProposalLogMapper.selectUserProposalLogList(userProposalLog);
    }

    /**
     * 新增投票记录
     * 
     * @param userProposalLog 投票记录
     * @return 结果
     */
    @Override
    public int insertUserProposalLog(UserProposalLog userProposalLog)
    {
        return userProposalLogMapper.insertUserProposalLog(userProposalLog);
    }

    /**
     * 修改投票记录
     * 
     * @param userProposalLog 投票记录
     * @return 结果
     */
    @Override
    public int updateUserProposalLog(UserProposalLog userProposalLog)
    {
        return userProposalLogMapper.updateUserProposalLog(userProposalLog);
    }

    /**
     * 删除投票记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserProposalLogByIds(String ids)
    {
        return userProposalLogMapper.deleteUserProposalLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除投票记录信息
     * 
     * @param id 投票记录ID
     * @return 结果
     */
    @Override
    public int deleteUserProposalLogById(Long id)
    {
        return userProposalLogMapper.deleteUserProposalLogById(id);
    }
}
