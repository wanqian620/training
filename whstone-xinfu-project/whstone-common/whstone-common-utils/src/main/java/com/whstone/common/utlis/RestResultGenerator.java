package com.whstone.common.utlis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whstone.common.constant.ResponseErrorEnum;
import com.whstone.common.constant.ResponseSuccessEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生成Rest风格的结果
 */
public class RestResultGenerator {

    private static final Logger logger = LoggerFactory.getLogger(RestResultGenerator.class);

    public static <T> HttpResponse<T> genSuccessResult(T data, ResponseSuccessEnum successEnum) {
        HttpResponse<T> result = new HttpResponse<>();
        result.setInfo(successEnum).setData(data);
        logger.debug("responseResult : {}", JSON.toJSONString(result));
        return result;
    }


    public static <T> HttpResponse<T> genErrorResult(ResponseErrorEnum errorEnum) {
        HttpResponse<T> result = new HttpResponse<>();
        result.setInfo(errorEnum);
        logger.debug("error: {}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 用于数据推送
     * @param data
     * @param successEnum
     * @param type : Device、Oracle、SqlServer
     * @param item : cpu、memory、storage、system、alert、eback
     * @return
     */
    public static String genJsonSuccessResult(Object data, ResponseSuccessEnum successEnum, String type,String item) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",successEnum.code);
        jsonObject.put("msg",successEnum.name);
        jsonObject.put("type",type);
        jsonObject.put("item",item);
        jsonObject.put("data",data);
        logger.debug("webSocketResult: {}", jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

    /**
     * 用于数据推送
     * @param errorEnum
     * @param type
     * @return
     */
    public static String genJsonErrorResult(ResponseErrorEnum errorEnum,String type) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",errorEnum.code);
        jsonObject.put("type",type);
        jsonObject.put("msg",errorEnum.name);
        logger.debug("webSocketError: {}", jsonObject.toJSONString());
        return jsonObject.toJSONString();
    }

}
