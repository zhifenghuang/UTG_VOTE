package com.hilamg.api.controller;


import com.hilamg.api.dto.in.CreateComment;
import com.hilamg.api.entity.UserAddress;
import com.hilamg.api.service.IUserCommentService;
import com.hilamg.common.annotations.LogAnnotation;
import com.hilamg.common.annotations.LoginUserAnnotation;
import com.hilamg.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 用户空间 前端控制器
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@RestController
@RequestMapping("/api/userComment")
public class UserCommentController {

    @Autowired
    IUserCommentService userCommentService;

    @PostMapping("createComment")
    @LogAnnotation(operateType = "评论", operateContent = "创建评论")
    public Result createComment(@LoginUserAnnotation UserAddress userAddress, @Valid CreateComment createComment) {
        return userCommentService.addComment(userAddress, createComment);
    }

    @GetMapping("getCommentList")
    @LogAnnotation(operateType = "评论", operateContent = "获取评论列表")
    public Result getProposalList(Integer proposalId) {
        return userCommentService.getCommentList(proposalId);
    }


}
