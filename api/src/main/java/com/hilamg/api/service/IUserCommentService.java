package com.hilamg.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hilamg.api.dto.in.CreateComment;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserComment;
import com.hilamg.common.result.Result;

import javax.validation.Valid;

/**
 * <p>
 * 用户空间 服务类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
public interface IUserCommentService extends IService<UserComment> {

    Result addComment(UserAddress userAddress, CreateComment createComment);


    Result getCommentList(Integer proposalId);

}
