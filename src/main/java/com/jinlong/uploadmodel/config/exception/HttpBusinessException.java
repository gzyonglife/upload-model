package com.jinlong.uploadmodel.config.exception;

import lombok.Getter;

/**
 * @description: 业务异常
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/20 11:38
 */
@Getter
public class HttpBusinessException extends RuntimeException {

    private static final long serialVersionUID = 2607703473231883942L;
    private final int code;

    public HttpBusinessException(int code) {
        super();
        this.code = code;
    }

    public HttpBusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public HttpBusinessException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public HttpBusinessException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    @Override
    public String toString() {
        return "{code:" + code + ",message:\"" + getMessage() + "\"}";
    }
}
