package com.ruoyi.utg.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户加入空间列对象 user_join_space
 * 
 * @author hh
 * @date 2022-05-31
 */
public class UserJoinSpace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 加入空间的用户Id */
    private Long userId;

    @Excel(name = "用户地址")
    private String userAddress;

    /** 空间Id */
    private Long space;

    @Excel(name = "空间Id")
    private String spaceName;

    /** 身份 */
    @Excel(name = "身份")
    private String isOwer;

    /** 加入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** $column.columnComment */
    private Date updateDate;

    /** $column.columnComment */
    private String isDelete;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSpace(Long space) 
    {
        this.space = space;
    }

    public Long getSpace() 
    {
        return space;
    }
    public void setIsOwer(String isOwer) 
    {
        this.isOwer = isOwer;
    }

    public String getIsOwer() 
    {
        return isOwer;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("space", getSpace())
            .append("isOwer", getIsOwer())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("isDelete", getIsDelete())
            .append("remarks", getRemarks())
            .toString();
    }
}
