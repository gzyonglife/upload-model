package com.jinlong.uploadmodel.entity.vo;

import com.jinlong.uploadmodel.config.exception.HttpBusinessException;
import lombok.Data;

/**
 * @description: ExceptionResponse
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 15:34
 */
@Data
public class ExceptionResponse {
    private int code;
    private String message;

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ExceptionResponse(HttpBusinessException exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }
    public ExceptionResponse() {
    }
}
