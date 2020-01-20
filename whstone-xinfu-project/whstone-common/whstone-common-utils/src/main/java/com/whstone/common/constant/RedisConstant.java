package com.whstone.common.constant;

/**
 * Redis key的管理
 * Mr.Gx
 */
public interface RedisConstant {

    /**
     * 设置30分钟有效时间 单位：秒
     */
    long REDIS_THIRTY_MINUTES = 1800;

    /**
     * 设置1天有效时间 单位：秒
     */
    long REDIS_ONE_DAY = 86400;

    /**
     * 设置3天有效时间 单位：秒
     */
    long REDIS_THREE_DAY = 259200;

    /**
     * 设置7天有效时间 单位：秒
     */
    long REDIS_SEVEN_DAY = 604800;

    /**
     * 根据登陆标识存储用户信息
     */
    String XFEY_LOGIN_TOKEN = "XFEY_TOKEN_";

    String XFEY_USER_ROLE = "XFEY_USER_ROLE";

    String XFEY_USER_INFO = "XFEY_USER_INFO";
}
