package com.whstone.common.utlis;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whstone.common.constant.ResponseErrorEnum;
import com.whstone.common.constant.ResponseSuccessEnum;

public class HttpResponse<T> {

    @JSONField(serialzeFeatures = SerializerFeature.NotWriteDefaultValue)
    private Integer code;

    private String msg;

    private T data;

    public HttpResponse setInfo(ResponseErrorEnum errorEnum) {
        this.code = errorEnum.code;
        this.msg = errorEnum.name;
        return this;
    }

    public HttpResponse setInfo(ResponseSuccessEnum successEnum) {
        this.code = successEnum.code;
        this.msg = successEnum.name;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
