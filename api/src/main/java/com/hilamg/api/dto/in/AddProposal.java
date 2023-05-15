package com.hilamg.api.dto.in;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class AddProposal {

    /**
     * 空间id
     */
    @NotNull(message = "not_has_space")
    private Integer spaceId;

    /**
     * 提案标题
     */
    @NotBlank(message = "tittle_not_null")
    private String title;

    /**
     * 描述
     */
    private String describeValue;

    /**
     * 讨论
     */
    private String discuss;

    /**
     * 投票类型 1:质押投票,2:销毁投票
     */
    @NotBlank(message = "kind_not_null")
    private String kind;

    /**
     * 投票类型 1:质押投票,2:销毁投票
     */
    @NotBlank(message = "voteType_not_null")
    private String voteType;

    /**
     * 开始时间
     */
    @NotNull(message = "startTime_not_null")
    private Long startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "endTime_not_null")
    private Long endTime;

    /**
     * 投票选项
     */
    @NotNull(message = "proposalOptions_not_null")
    private String proposalOptions;

    /**
     * 票价
     */
    private BigDecimal bili;

    /**
     * 图片链接
     */
    private String imageUrl;

}
