package com.jinlong.uploadmodel.config.exception;

/**
 * @description: 自定义异常枚举
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/20 11:19
 */
public enum CustomExceptionEnum {
    //404 not find
    NOT_FIND(new HttpBusinessException("该资源未找到", 404));

    CustomExceptionEnum(RuntimeException exception) {
        this.exception = exception;
    }

    public RuntimeException getException() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    private RuntimeException exception;
}
