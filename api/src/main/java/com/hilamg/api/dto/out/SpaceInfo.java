package com.hilamg.api.dto.out;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class SpaceInfo {

    private Integer id;

    private String owner;

    // 1:未加入，2:已加入
    private String isJson;

    private String logo;

    private String name;

    private String symbol;

    private String contractAddress;

    private Integer maxSpace;

    private Integer maxHasSpace;

    private BigDecimal tokenAmount;

    private Integer spaceType;

    /**
     * 空间代币精度
     */
    private Integer decimals;

    private String twitterUrl;

    private String discordUrl;

    private String telegramUrl;

    private String githubUrl;

    private String gitbookUrl;

    private String webUrl;
}
