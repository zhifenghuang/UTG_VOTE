package com.hilamg.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 空间类型
 * </p>
 *
 * @author hehuan
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserSpaceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 空间类型中文
     */
    private String spaceName;

    /**
     * 空间类型英文
     */
    private String spaceNameEn;

    /**
     * 空间类型日语
     */
    private String spaceNameJp;

    /**
     * 空间类型日语
     */
    private String spaceNameKo;

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
