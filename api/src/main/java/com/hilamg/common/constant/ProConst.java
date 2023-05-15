package com.hilamg.common.constant;

/***
 * 业务常量
 */
public class ProConst {

    /**
     * 删除状态
     */
    public enum DeleteEnum {
        DELETE("已删除", "1"), NODELETE("未删除", "0");
        private String code;
        private String value;

        DeleteEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 封禁状态
     */
    public enum BannedEnum {
        DELETE("已封禁", "1"), NODELETE("未封禁", "0");
        private String code;
        private String value;

        BannedEnum(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }


    /**
     * 下载码状态
     */
    public enum code_status {
        newStatus("未使用", "1"),
        oldStatus("已分配使用", "2"),
        ;
        private String code;
        private String value;

        code_status(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    public enum is_ower {
        member("成员", "1"),
        ower("创建者", "2"),
        ;
        private String code;
        private String value;

        is_ower(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    public enum is_join {
        not_join("未加入", "1"),
        has_join("已加入", "2"),
        ;
        private String code;
        private String value;

        is_join(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    public enum dictKey {
        proposal_fee("发起提案手续费", "proposal_fee"),
        ;
        private String code;
        private String value;

        dictKey(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }


    public enum isOneLine {
        not_one_line("未上链", "1"),
        has_one_line("已上链", "2"),
        ;
        private String code;
        private String value;

        isOneLine(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    public enum proposalStatus {
        not_open("待开始", "1"),
        has_open("活跃", "2"),
        end("已关闭", "3"),
        ;
        private String code;
        private String value;

        proposalStatus(String code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }
}
