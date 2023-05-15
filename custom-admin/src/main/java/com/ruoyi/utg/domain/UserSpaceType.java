package com.ruoyi.utg.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 空间类型对象 user_space_type
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public class UserSpaceType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空间类型中文 */
    @Excel(name = "空间类型中文")
    private String spaceName;

    /** 空间类型英文 */
    @Excel(name = "空间类型英文")
    private String spaceNameEn;

    /** 空间类型日语 */
    @Excel(name = "空间类型日语")
    private String spaceNameJp;

    /** 空间类型日语 */
    @Excel(name = "空间类型日语")
    private String spaceNameKo;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** $column.columnComment */
    private String isDelete;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSpaceName(String spaceName) 
    {
        this.spaceName = spaceName;
    }

    public String getSpaceName() 
    {
        return spaceName;
    }
    public void setSpaceNameEn(String spaceNameEn) 
    {
        this.spaceNameEn = spaceNameEn;
    }

    public String getSpaceNameEn() 
    {
        return spaceNameEn;
    }
    public void setSpaceNameJp(String spaceNameJp) 
    {
        this.spaceNameJp = spaceNameJp;
    }

    public String getSpaceNameJp() 
    {
        return spaceNameJp;
    }
    public void setSpaceNameKo(String spaceNameKo) 
    {
        this.spaceNameKo = spaceNameKo;
    }

    public String getSpaceNameKo() 
    {
        return spaceNameKo;
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
            .append("spaceName", getSpaceName())
            .append("spaceNameEn", getSpaceNameEn())
            .append("spaceNameJp", getSpaceNameJp())
            .append("spaceNameKo", getSpaceNameKo())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("isDelete", getIsDelete())
            .append("remarks", getRemarks())
            .toString();
    }
}
