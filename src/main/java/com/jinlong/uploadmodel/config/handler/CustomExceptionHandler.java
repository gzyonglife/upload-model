package com.jinlong.uploadmodel.config.handler;

import com.jinlong.uploadmodel.config.exception.HttpBusinessException;
import com.jinlong.uploadmodel.entity.vo.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description: 自动义异常全局处理器
 * @program: advice-demo
 * @author: jinlong
 * @time: 2020/5/18 17:25
 */
@Slf4j
@ControllerAdvice
@ConditionalOnWebApplication
public class CustomExceptionHandler {
    @ResponseBody
    @ExceptionHandler(HttpBusinessException.class)
    ExceptionResponse httpBusinessException(HttpBusinessException e) {
        log.warn("业务发生异常，堆栈跟踪信息为:", e);
        return new ExceptionResponse(e.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ExceptionResponse validationException(MethodArgumentNotValidException e) {
        log.warn("业务发生异常，堆栈跟踪信息为:", e);
        return new ExceptionResponse(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
