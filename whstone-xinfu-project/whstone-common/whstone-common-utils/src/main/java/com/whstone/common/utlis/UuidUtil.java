package com.whstone.common.utlis;

import java.util.UUID;

/**
 * 生成UUID
 */
public class UuidUtil {

    public static String getUUid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getUUidToToken(){
        return "XFEY-" + UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUid());
    }
}
