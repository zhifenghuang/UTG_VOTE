package com.hilamg.common.constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public enum SerialNoEnum {
    /**
     * 充提币相关
     */
    TxRECHARGE(1, "充币"),
    TxCASH(2, "提币"),
    TxTRANSFER(3, "转账"),
    BUY_MINE(4, "购买矿机"),
    TxSALEIN(5, "销售提成"),
    TxFR(6, "分润"),
    TO_YY(8, "划转到预约钱包"),
    TXTBBH(7, "提币驳回"),
    TXDIG_IN(10, "挖矿收益"),
    Pool_FH(11, "矿池分红"),
    JC_ZY(12, "解除质押币"),
    ADD_DK(13, "缴纳带宽费"),
    JSON_GAME(9, "众筹正常参与"),
    YY_JSON_GAME(14, "众筹提前预约"),
    ZC_COME_IN(15, "众筹收益"),
    J_INVITE_COME_IN(16, "间推奖励"),
    Z_INVITE_COME_IN(17, "直推奖励"),
    TEAM_COME_IN(18, "社区奖励"),
    EQ_LEVEL_COME_IN(19, "平级奖励"),
    RETURN_ZC_MONEY(20, "退回本金"),
    WITHDRAWAL_MINEFEE(21, "提币消耗"),
    TOP10(22, "TOP10亏损奖励"),
    TOP100(23, "TOP100亏损奖励"),
    WAXP_REWARD(24, "WAXP奖励"),
    LOCK_RELEASE(25, "静态释放"),
    ;

    private Integer code;
    private String name;

    SerialNoEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static String generateSerialNo(Integer code, Long userId){
        String serialNo = "";
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        int random = new Random().nextInt(89) + 10;
        serialNo = code + userId + date + String.valueOf(random);
        return serialNo;
    }
}
