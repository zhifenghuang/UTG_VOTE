package com.ruoyi.utg.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 投票记录对象 user_proposal_log
 * 
 * @author hh
 * @date 2022-05-31
 */
public class UserProposalLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 投票用户 */
    private Long userId;

    @Excel(name = "投票用户")
    private String userAddress;

    /** 所属空间 */
    private Long spaceId;

    @Excel(name = "所属空间")
    private String spaceName;

    /** 提案标题 */

    private Long proposalId;

    @Excel(name = "提案标题")
    private String title;

    /** 选项内容 */
    private Long optionsId;

    @Excel(name = "选项内容")
    private String optionsName;

    /** 投票数量 */
    @Excel(name = "投票数量")
    private BigDecimal amount;

    /** 是否退票，1:不需要退票,2:未退票,3:已退回 */
    @Excel(name = "是否退票，1:不需要退票,2:未退票,3:已退回")
    private String status;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionsName() {
        return optionsName;
    }

    public void setOptionsName(String optionsName) {
        this.optionsName = optionsName;
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
    public void setSpaceId(Long spaceId) 
    {
        this.spaceId = spaceId;
    }

    public Long getSpaceId() 
    {
        return spaceId;
    }
    public void setProposalId(Long proposalId) 
    {
        this.proposalId = proposalId;
    }

    public Long getProposalId() 
    {
        return proposalId;
    }
    public void setOptionsId(Long optionsId) 
    {
        this.optionsId = optionsId;
    }

    public Long getOptionsId() 
    {
        return optionsId;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("spaceId", getSpaceId())
            .append("proposalId", getProposalId())
            .append("optionsId", getOptionsId())
            .append("amount", getAmount())
            .append("status", getStatus())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("isDelete", getIsDelete())
            .append("remarks", getRemarks())
            .toString();
    }
}
