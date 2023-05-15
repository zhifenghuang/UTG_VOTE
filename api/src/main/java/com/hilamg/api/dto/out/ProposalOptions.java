package com.hilamg.api.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 投票选项以及票数列表
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProposalOptions {

    private Integer id;

    /**
     * 投票个数
     */
    private BigDecimal account;

    /**
     * 投票内容
     */
    private String options;
}
