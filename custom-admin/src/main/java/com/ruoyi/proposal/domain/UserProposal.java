package com.ruoyi.proposal.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 提案列对象 user_proposal
 * 
 * @author hh
 * @date 2022-05-31
 */
public class UserProposal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空间名称 */
    private Long spaceId;

    @Excel(name = "空间名称")
    private String spaceName;

    /** 提案发起者 */
    private Long proposalUser;

    @Excel(name = "提案发起者")
    private String proposalUserAddress;

    /** 提案标题 */
    @Excel(name = "提案标题")
    private String title;

    /** 描述 */
    @Excel(name = "描述")
    private String describeValue;

    /** 讨论 */
    @Excel(name = "讨论")
    private String discuss;

    /** 投票类型 */
    @Excel(name = "投票类型")
    private String kind;

    /** 消耗代币数量 */
    @Excel(name = "消耗代币数量")
    private BigDecimal tokenAmount;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 提案状态 */
    @Excel(name = "提案状态")
    private String status;

    /** 发布状态 */
    @Excel(name = "发布状态")
    private String isOneLine;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** $column.columnComment */
    private Date updateDate;

    /** $column.columnComment */
    private String isDelete;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    @Excel(name = "单票价值")
    private BigDecimal bili;

    public BigDecimal getBili() {
        return bili;
    }

    public void setBili(BigDecimal bili) {
        this.bili = bili;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getProposalUserAddress() {
        return proposalUserAddress;
    }

    public void setProposalUserAddress(String proposalUserAddress) {
        this.proposalUserAddress = proposalUserAddress;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSpaceId(Long spaceId) 
    {
        this.spaceId = spaceId;
    }

    public Long getSpaceId() 
    {
        return spaceId;
    }
    public void setProposalUser(Long proposalUser) 
    {
        this.proposalUser = proposalUser;
    }

    public Long getProposalUser() 
    {
        return proposalUser;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setDescribeValue(String describeValue) 
    {
        this.describeValue = describeValue;
    }

    public String getDescribeValue() 
    {
        return describeValue;
    }
    public void setDiscuss(String discuss) 
    {
        this.discuss = discuss;
    }

    public String getDiscuss() 
    {
        return discuss;
    }
    public void setKind(String kind) 
    {
        this.kind = kind;
    }

    public String getKind() 
    {
        return kind;
    }
    public void setTokenAmount(BigDecimal tokenAmount) 
    {
        this.tokenAmount = tokenAmount;
    }

    public BigDecimal getTokenAmount() 
    {
        return tokenAmount;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setIsOneLine(String isOneLine) 
    {
        this.isOneLine = isOneLine;
    }

    public String getIsOneLine() 
    {
        return isOneLine;
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
            .append("spaceId", getSpaceId())
            .append("proposalUser", getProposalUser())
            .append("title", getTitle())
            .append("describeValue", getDescribeValue())
            .append("discuss", getDiscuss())
            .append("kind", getKind())
            .append("tokenAmount", getTokenAmount())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("isOneLine", getIsOneLine())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("isDelete", getIsDelete())
            .append("remarks", getRemarks())
            .toString();
    }
}
