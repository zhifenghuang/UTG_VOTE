package com.hilamg.api.mapper;

import com.hilamg.api.entity.UserProposalOptions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 提案投票选项 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-26
 */
public interface UserProposalOptionsMapper extends BaseMapper<UserProposalOptions> {

    List<Integer> getIdList(Integer proposalId);

}
