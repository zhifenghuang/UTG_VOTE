package com.hilamg.api.dto.out;

import lombok.Data;

import java.util.Date;

@Data
public class UserProposalList {
    private Integer id;

    private Integer spaceId;

    private String title;

    /**
     * 投票类型 1:质押投票,2:销毁投票
     */
    private String kind;

    private String voteType;

    /**
     * 1:待开始,2:活跃,3:已关闭
     */
    private String status;

    /**
     * 提案到期时间
     */
    private Long subDateTime;

    /**
     * 提案发起者
     */
    private String proposalUserAddress;

    /**
     * 提案发起时间
     */
    private Date createDate;

    /**
     * 提案内容
     */
    private String describeValue;

    /**
     * 讨论
     */
    private String discuss;

    private String nikeName;

    private String spaceName;
}
