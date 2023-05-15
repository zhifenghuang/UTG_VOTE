package com.hilamg.api.dto.out;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProposalInfo {
    /**
     * 标题
     */
    private String title;

    /**
     * 提案发起者
     */
    private String proposalUser;

    /**
     * 空间地址
     */
    private String spaceAddress;

    /**
     * 描述
     */
    private String describeValue;

    /**
     * 讨论
     */
    private String discuss;

    /**
     * 投票类型
     */
    private String kind;


    private String voteType;

    private Date startTime;

    private Date endTime;

    private String status;

    /**
     * 代币符号
     */
    private String symbol;

    /**
     * 投票选项以及结果
     */
    private List<ProposalOptions> proposalOptions;

    /**
     * 投票用户列表
     */
    private List<ProposalLogList> proposalLogList;

    /**
     * 待赎回金额
     */
    private BigDecimal amountRedeemed;

    /**
     * 票价
     */
    private BigDecimal bili;

    /**
     * 图片链接
     */
    private String imageUrl;


}
