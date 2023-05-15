package com.hilamg.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 提案列表
 * </p>
 *
 * @author hehuan
 * @since 2022-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 二层回复评论者ID
     */
    private Integer replaySubUserId;

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
     * 创建时间
     */
    private Date createDate;

    private Date updateDate;

    private String isDelete;


}
