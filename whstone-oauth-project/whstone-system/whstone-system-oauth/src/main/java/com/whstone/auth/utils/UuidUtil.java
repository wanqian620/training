package com.whstone.auth.utils;

import java.util.UUID;

/**
 * 生成UUID
 */
public class UuidUtil {

    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
