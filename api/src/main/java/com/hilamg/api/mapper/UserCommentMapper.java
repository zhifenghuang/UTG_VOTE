package com.hilamg.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hilamg.api.dto.out.UserCommentList;
import com.hilamg.api.entity.UserComment;
import jnr.ffi.annotations.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户空间 Mapper 接口
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface UserCommentMapper extends BaseMapper<UserComment> {

    List<UserCommentList> getUserCommentList(@Param("proposalId") Integer proposalId);

}
