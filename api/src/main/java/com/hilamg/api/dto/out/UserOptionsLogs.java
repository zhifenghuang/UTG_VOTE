package com.hilamg.api.dto.out;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserOptionsLogs {

    /**
     * 投票金额
     */
    private BigDecimal amount;

    /**
     * 投票时间
     */
    private Date createDate;

    /**
     * 提案标题
     */
    private String title;

    /**
     * 选项
     */
    private String options;

  /**
   * 提案选项id
   */
  private Integer optionsId;

  /**
   * 提案id
   */
  private Integer proposalId;

  /**
   * 提案名称
   */
  private String name;

  /**
   * 空间id
   */
  private Integer spaceId;

  private String logo;

}
