package com.hilamg.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户空间
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 空间创建者Id
     */
    private Integer ownerId;

    /**
     * 空间类型
     */
    private Integer spaceType;

    /**
     * 发起提案需要消耗的代币数量
     */
    private BigDecimal tokenAmount;

    /**
     * 空间图片
     */
    private String logo;

    /**
     * 空间名称
     */
    private String name;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 合约地址
     */
    private String contractAddress;

    /**
     * 空间代币精度
     */
    private Integer decimals;

    /**
     * 提案阈值
     */
    private Integer maxSpace;

    /**
     * 提案生效阈值
     */
    private Integer maxHasSpace;

    /**
     * 空间审核状态 1:审核中,2:审核成功,3:审核被拒
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

    private String twitterUrl;

    private String discordUrl;

    private String telegramUrl;

    private String githubUrl;

    private String gitbookUrl;

    private String webUrl;


}
