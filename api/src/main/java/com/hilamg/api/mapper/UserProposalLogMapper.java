package com.hilamg.api.mapper;

import com.hilamg.api.dto.out.ProposalLogList;
import com.hilamg.api.entity.UserProposalLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 投票记录 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-29
 */
public interface UserProposalLogMapper extends BaseMapper<UserProposalLog> {

    BigDecimal getSumAmountById(Integer id);

    List<ProposalLogList> getLogs(@Param("id") Integer id,@Param("userId") Integer userId);

    BigDecimal getMoney(@Param("proposalId") Integer proposalId,@Param("userId") Integer userId);

}
