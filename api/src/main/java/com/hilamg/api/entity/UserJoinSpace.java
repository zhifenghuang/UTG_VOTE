package com.hilamg.api.entity;

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
 * 用户加入空间列表
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserJoinSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 加入空间的用户Id
     */
    private Integer userId;

    /**
     * 空间Id
     */
    private Integer space;

    /**
     * 1:普通成员,2:创建者
     */
    private String isOwer;

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


}
