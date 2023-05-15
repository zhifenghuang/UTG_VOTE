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
 * 投票记录
 * </p>
 *
 * @author hehuan
 * @since 2022-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserProposalLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 投票用户
     */
    private Integer userId;

    /**
     * 空间id
     */
    private Integer spaceId;

    /**
     * 投票提案id
     */
    private Integer proposalId;

    /**
     * 选项id
     */
    private Integer optionsId;

    /**
     * 投票数量
     */
    private BigDecimal amount;

    /**
     * 是否退票，1:不需要退票,2:未退票,3:已退回
     */
    private String status;

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

    private Long timeTimeMillis;


}
