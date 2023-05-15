package com.hilamg.api.dto.in;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateComment {

    /**
     * 提案id
     */
    @NotBlank(message = "proposalId_is_null")
    private Integer proposalId;


    /**
     * 二层回复评论者ID
     */
    private Integer replaySubUserId;

    /**
     * 评论内容
     */
    @NotBlank(message = "content_is_null")
    private String content;

    /**
     * 回复的评论id
     */
    private Integer replayId;

    /**
     * 第二层回复的评论id
     */
    private Integer replaySubId;

}
