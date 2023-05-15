package com.hilamg.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hilamg.api.dto.in.CreateComment;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.entity.UserComment;
import com.hilamg.api.mapper.UserCommentMapper;
import com.hilamg.api.service.IUserCommentService;
import com.hilamg.common.result.Result;
import com.hilamg.common.result.ResultGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * <p>
 * 用户空间 服务实现类
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentMapper, UserComment> implements IUserCommentService {

    @Autowired
    UserCommentMapper userCommentMapper;


    @Override
    public Result addComment(UserAddress userAddress, CreateComment createComment) {

        UserComment comment = new UserComment();
        BeanUtils.copyProperties(createComment, comment);
        comment.setCommentUserId(userAddress.getId());
        comment.setCreateDate(new Date());
        userCommentMapper.insert(comment);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result getCommentList(Integer proposalId) {
        return ResultGenerator.genSuccessResult(userCommentMapper.getUserCommentList(proposalId));
    }
}
