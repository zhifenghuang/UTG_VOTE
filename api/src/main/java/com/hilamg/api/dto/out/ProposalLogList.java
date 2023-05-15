package com.hilamg.api.dto.out;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 投票日志统计
 */
@Data
public class ProposalLogList {
    private Integer userId;

    private String address;

    private BigDecimal amount;

    /**
     * 是否退票，1:不需要退票,2:未退票,3:已退回
     */
    private String status;
}
