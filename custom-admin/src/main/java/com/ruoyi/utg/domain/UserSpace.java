package com.ruoyi.utg.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户空间对象 user_space
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public class UserSpace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空间创建者Id */

    private Long ownerId;
    @Excel(name = "空间创建者")
    private String ownerAddress;

    /** 空间类型 */
    private Long spaceType;

    /** 空间类型 */
    @Excel(name = "空间类型")
    private String spaceTypeName;

    /** 空间图片 */
    @Excel(name = "空间图片")
    private String logo;

    /** 空间名称 */
    @Excel(name = "空间名称")
    private String name;

    /** 符号 */
    @Excel(name = "符号")
    private String symbol;

    /** 合约地址 */
    @Excel(name = "合约地址")
    private String contractAddress;

    /** 发起提案需要消耗的代币数量 */
    @Excel(name = "发起提案需要消耗的代币数量")
    private BigDecimal tokenAmount;

    /** 提案阈值 */
    @Excel(name = "提案阈值")
    private Long maxSpace;

    /** 提案生效阈值 */
    @Excel(name = "提案生效阈值")
    private Long maxHasSpace;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String status;

    @Excel(name = "推特")
    private String twitterUrl;

    @Excel(name = "电报")
    private String telegramUrl;

    @Excel(name = "discord")
    private String discordUrl;

    @Excel(name = "github")
    private String githubUrl;

    @Excel(name = "gitbook")
    private String gitbookUrl;

    @Excel(name = "官网链接")
    private String webUrl;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getTelegramUrl() {
        return telegramUrl;
    }

    public void setTelegramUrl(String telegramUrl) {
        this.telegramUrl = telegramUrl;
    }

    public String getDiscordUrl() {
        return discordUrl;
    }

    public void setDiscordUrl(String discordUrl) {
        this.discordUrl = discordUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getGitbookUrl() {
        return gitbookUrl;
    }

    public void setGitbookUrl(String gitbookUrl) {
        this.gitbookUrl = gitbookUrl;
    }

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

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getSpaceTypeName() {
        return spaceTypeName;
    }

    public void setSpaceTypeName(String spaceTypeName) {
        this.spaceTypeName = spaceTypeName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOwnerId(Long ownerId) 
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() 
    {
        return ownerId;
    }
    public void setSpaceType(Long spaceType) 
    {
        this.spaceType = spaceType;
    }

    public Long getSpaceType() 
    {
        return spaceType;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public String getSymbol() 
    {
        return symbol;
    }
    public void setContractAddress(String contractAddress) 
    {
        this.contractAddress = contractAddress;
    }

    public String getContractAddress() 
    {
        return contractAddress;
    }
    public void setTokenAmount(BigDecimal tokenAmount) 
    {
        this.tokenAmount = tokenAmount;
    }

    public BigDecimal getTokenAmount() 
    {
        return tokenAmount;
    }
    public void setMaxSpace(Long maxSpace) 
    {
        this.maxSpace = maxSpace;
    }

    public Long getMaxSpace() 
    {
        return maxSpace;
    }
    public void setMaxHasSpace(Long maxHasSpace) 
    {
        this.maxHasSpace = maxHasSpace;
    }

    public Long getMaxHasSpace() 
    {
        return maxHasSpace;
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
            .append("ownerId", getOwnerId())
            .append("spaceType", getSpaceType())
            .append("logo", getLogo())
            .append("name", getName())
            .append("symbol", getSymbol())
            .append("contractAddress", getContractAddress())
            .append("tokenAmount", getTokenAmount())
            .append("maxSpace", getMaxSpace())
            .append("maxHasSpace", getMaxHasSpace())
            .append("status", getStatus())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("isDelete", getIsDelete())
            .append("remarks", getRemarks())
            .toString();
    }
}
