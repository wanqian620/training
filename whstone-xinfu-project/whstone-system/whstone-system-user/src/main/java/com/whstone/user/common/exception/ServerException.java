package com.whstone.user.common.exception;


import com.whstone.common.constant.ResponseErrorEnum;
import com.whstone.common.utlis.HttpResponse;

/**
 * @author Mr.Gx
 */
public class ServerException extends Exception {

    private static final long serialVersionUID = 1L;

    private ResponseErrorEnum errorEnum;

    private HttpResponse response;


    public ServerException(ResponseErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public ServerException(String msg) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setMsg(msg);
        httpResponse.setCode(500);
        this.response = httpResponse;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public ResponseErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ResponseErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }
}
