package com.framework.loippi.controller.app.common;

/**
 * Created by longbh on 16/9/5.
 */
public class AppConstants {
    /**
     * 成功
     */
    public static final int OK = 2000000;

    /**
     * 通用失败
     */
    public static final int FAIL = 5000000;
    /**
     * 找不到
     */
    public static final int NOT_FOUND = 5000001;

    /**
     * 用户会话失效
     */
    public static final int USER_SESS_EXPIRED = 5000103;

    /**
     * 用户未登录
     */
    public static final int USER_NOT_LOGIN = 5000104;
    
    
    /**
     * 帐号或密码错误
     */
    public static final int USER_NOT_INFO = 5000105;
    
    /**
     * 帐号不存在
     */
    public static final int  ACCOUNT_NOT_EXIST = 5000106;
    
    /**
     * 验证码错误
     */
    public static final int  CAPTCHA__NOT_EXIST = 5000107;
    
    /**
     * 参数不完整
     */
    public static final int  PARAMETER__NOT_COMPLETE = 5000108;
    
    /**
     * 该账号在其他手机登录
     */
    public static final int USER_ACCOUNT_LOGINED = 5000413;

    /**
     * 用户已经被禁用
     */
    public static final int LOCK_USER = 5000504;
    
    
    /**
     * 该手机号已被注册
     */
    public static final int IS_USER = 5000509;

    /**
     * 手机号码不存在
     */
    public static final int USER_NOT_FOUND = 5000505;

    /**
     * 无版本更新
     */
    public static final int NO_NEW = 5000506;
    
    /**
     * 已经是好友关系
     */
    public static final int IS_FRIEND = 5000519;
    
    /**
     * 课程已下架
     */
    public static final int Order_DOWN = 5000701;
    /**
     * 报名人数已满，请选择其他课程
     */
    public static final int Order_OUT = 5000702;
    
    /**
     * 报名人数已满，请选择其他课程
     */
    public static final int PASSWORD__ERROR = 5000704;
    
    /**
     * 支付失败
     */
    public static final int Order_FAILE = 5000700;
    
    public static final int inrollcalltime = 5000703;
    
    public static final int HAVE_ORG = 5000705;
    
    /**
     * 需要发布行程才能完成任务 请先发布一条个人动态
     */
    public static final int TRIP__ERROR = 5000706;


    //-------------------二期----------------------------
    public static final int CODE_EXPIRE = 5000601;      //验证码失效

    public static final int UNABLE_UPDATE_OTHER = 5000602;  //香氛以外的不能修改

}
