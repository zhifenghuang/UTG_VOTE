package com.hilamg.api.dto.in;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UpdateSpace {

    private Integer id;

    @NotNull(message = "spaceType_is_null")
    private Integer spaceType;

    @NotBlank(message = "logo_is_null")
    private String logo;

    @NotBlank(message = "name_is_null")
    private String name;

    @NotBlank(message = "symbol_is_null")
    private String symbol;

    @NotBlank(message = "contractAddress_is_null")
    private String contractAddress;

    @Range(message = "maxSpace_is_main",min = 1)
    private Integer maxSpace;

    @Range(message = "maxHasSpace_is_main",min = 1)
    private Integer maxHasSpace;

    @NotNull(message = "tokenAmount_is_null")
    private BigDecimal tokenAmount;

    @NotNull(message = "decimals_is_null")
    private Integer decimals;

    private String twitterUrl;

    private String discordUrl;

    private String telegramUrl;

    private String githubUrl;

    private String gitbookUrl;

    private String webUrl;

}
