package com.hilamg.api.dto.out;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProposalSignInfo {

    /**
     *         uint256 proposalId;
     *         uint256 kind; // 1质押投票，2销毁投票
     *         uint256 spaceId; // 空间id
     *         address user;   // 提案发起者
     *         IERC20 token;   // 发起提案和投票需要的token，零地址代表使用的UTG链的公链币
     *         uint256 tokenAmount; // 发起提案消耗的代币数量
     *         uint256 startTime;  // 提案开始时间
     *         uint256 endTime;    // 提案结束时间
     *         string[] options;   // 提案的选项，按顺序组成一个数组，比如["第一个选项","第二个选项","第三个选项"]
     *         // bytes signature; // 签名值，对这五个参数proposalId, kind, spaceId, user, token, tokenAmount按顺序编码后进行签名
     */
    private String proposalId;
    private String kind;
    private String voteType;
    private String spaceId;
    private String user;
    private String token;
    private String tokenAmount;
    private String startTime;
    private String endTime;
    private List<String> options;
    private String signature;

}
