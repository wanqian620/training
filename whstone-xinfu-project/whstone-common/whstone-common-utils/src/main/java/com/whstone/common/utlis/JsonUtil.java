package com.whstone.common.utlis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

public class JsonUtil {

    /**
     * JSON字符串转对象
     * @param text
     * @param clazz
     */
    public static  <T> T parseObject(String text, Class<T> clazz) {
        try {
            return JSON.parseObject(text,clazz);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 字符串转集合
     * @param text
     * @param clazz
     * @return
     */
    public static  <T> List<T> parseArray(String text, Class<T> clazz) {
        try {
            return JSONArray.parseArray(text, clazz);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 对象转JSON字符串
     * @param obj
     * @return
     */
    public static String toJSON(Object obj){
        try {
            return JSON.toJSON(obj).toString();
        }catch (Exception e){
            return "";
        }
    }

}
