package com.hilamg.api.dto.out;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserCommentList {

    private Integer id;
    /**
     * 提案id
     */
    private Integer proposalId;

    /**
     * 评论者id
     */
    private Integer commentUserId;


    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复的评论id
     */
    private Integer replayId;

    /**
     * 第二层回复的评论id
     */
    private Integer replaySubId;

    /**
     * 二层回复评论者ID
     */
    private Integer replaySubUserId;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 回复的评论数
     */
    private Integer replayNum;
}
