package com.hilamg.common.constant;

/**
 * 常量
 */
public class Constant {

    /**
     * 国际化相关
     */
    public static final String ENGLISH = "en";
    public static final String SIMPLE_CHINESE = "zh";
    public static final String KOREAN = "ko";
    public static final String JAPANESE = "jpn";


    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * 助记词
     */
    public final static String MNEMONIC="memo";


    /**
     * 私钥
     */
    public final static String PRIKEY="priKey";


    /**
     * JWT密钥
     */
    public final static String TOKEN_SECRET = "glAlKKbo6Ang1Lao";

    /**
     * 默认头像
     */
    public final static String HEAD_PORTRAIT = "https://okchat-pro.oss-cn-shenzhen.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20210616094844.png";


    public final static String REDIS_KEY="_phoneId_";

    //验证码超时时间
    public final static long SMS_CODE_TIME = 60 * 5;//5分钟

   //同步锁时间
    public final static long LOCK_TIME = 5L;//5分钟



    //短信模板
    public final static String SMS_CHINA_CONTENT = "[HOHO] Dear user, your verification code is %s, and the verification code is valid within 5 minutes, please do not disclose it to others!";

   //手机号注册
    public final static  String SMS="sms";

    //邮箱注册
    public final static String EMAIL="email";

    //USDT-TRC20
    public final static String USDT="USDT-TRC20";

    //DHS
    public final static String DHS="DHS";

    public final static String BZZONE="Bzzone";
    public final static String NFT="NFT";
    public final static String WIKI="WiKi";
    public final static String WAXP="WAXP";
    public final static String Matebok="Matebok";

}
