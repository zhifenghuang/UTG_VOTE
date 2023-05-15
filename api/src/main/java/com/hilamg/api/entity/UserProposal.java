package com.hilamg.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class UserProposal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 空间id
     */
    private Integer spaceId;

    /**
     * 提案发起者
     */
    private Integer proposalUser;

    /**
     * 提案标题
     */
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
    private String kind;

    /**
     * 投次数 1:1次,2:多次
     */
    private String voteType;

    /**
     * 发起提案消耗的代币数量
     */
    private BigDecimal tokenAmount;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;


    private String status;

    /**
     * 1:未发布,2:已发布
     */
    private String isOneLine;

    /**
     * 创建时间
     */
    private Date createDate;

    private Date updateDate;

    private String isDelete;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 票价
     */
    private BigDecimal bili;

    private String imageUrl;


}
