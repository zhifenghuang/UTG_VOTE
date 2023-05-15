package com.hilamg.api.mapper;

import com.hilamg.api.dto.out.UserOptionsLogs;
import com.hilamg.api.dto.out.UserProposalList;
import com.hilamg.api.entity.UserProposal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 提案列表 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-26
 */
public interface UserProposalMapper extends BaseMapper<UserProposal> {

    List<UserProposalList> getUserProposalList(@Param("userId") Integer userId,@Param("spaceId") Integer spaceId,@Param("status") String status);

    List<UserProposalList> getTimeLineList(@Param("userId") Integer userId,@Param("type") String type,@Param("status") String status);

    List<UserOptionsLogs> getproposalUserLog(Integer userId);
}
