package com.whstone.user.common.exception;


import com.alibaba.fastjson.JSON;
import com.whstone.common.constant.ResponseErrorEnum;
import com.whstone.common.utlis.HttpResponse;
import com.whstone.common.utlis.RestResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler
 * @Author: Mr.Gx
 * @Date: 2019/12/3
 */
@RestControllerAdvice
public class ServerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ServerExceptionHandler.class);


    @ExceptionHandler(value = ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse defaultErrorHandler(ServerException ex) throws Exception {
        if (ex.getResponse() != null) {
            logger.debug("responseResult : {}", JSON.toJSONString(ex.getResponse()));
            return ex.getResponse();
        }
        return RestResultGenerator.genErrorResult(ex.getErrorEnum());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse defaultErrorHandler(Exception ex) throws Exception {
        return RestResultGenerator.genErrorResult(ResponseErrorEnum.SYSTEM_INNER_ERROR);
    }
}
