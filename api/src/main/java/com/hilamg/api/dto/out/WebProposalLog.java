package com.hilamg.api.dto.out;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
public class WebProposalLog {
    private String address;

    private BigInteger amount;

    private Integer option;

    private Long time;
}
