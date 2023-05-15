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
 * 用户地址信息
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 用户昵称
     */
    private String nikeName;

    /**
     * 用户简介
     */
    private String userInfo;

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
