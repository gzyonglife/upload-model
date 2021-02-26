package com.jinlong.uploadmodel.util;

import com.jinlong.uploadmodel.config.exception.HttpBusinessException;

/**
 * @description: 自定义异常枚举
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/20 11:19
 */
public enum CustomExceptionEnum {

    NOT_FIND(new HttpBusinessException("该资源未找到", 404)),
    TOKEN_OVERDUE(new HttpBusinessException("token过期", 401)),
    TOKEN_ERROR(new HttpBusinessException("token错误", 400)),
    CREATE_USER_ERROR(new HttpBusinessException("创建用户错误", 400)),
    MODIFY_USER_ERROR(new HttpBusinessException("修改用户错误", 400)),
    AUTHORITY_ERROR(new HttpBusinessException("权限不足", 403)),
    USER_DISABLE(new HttpBusinessException("用户被禁用", 406)),
    ACCOUNT_OVERDUE(new HttpBusinessException("账号过期", 406)),
    CREATE_PLAN_ERROR(new HttpBusinessException("添加计划实施信息出错", 500)),
    LOGIN_ERROR(new HttpBusinessException("登录失败", 406)),
    CREDENTIALS_OVERDUE(new HttpBusinessException("证书过期", 406)),
    ACCOUNT_DISABLE(new HttpBusinessException("账户被锁定", 406)),
    ACCOUNT_PASSWORD_ERROR(new HttpBusinessException("账号或密码错误", 406)),
    GET_NONE(new HttpBusinessException("未获取到数据", 404)),
    SERVER_ERROR(new HttpBusinessException("服务器异常，请稍后再试",500));


    CustomExceptionEnum(HttpBusinessException exception) {
        this.exception = exception;
    }

    public HttpBusinessException getException() {
        return exception;
    }

    public void setException(HttpBusinessException exception) {
        this.exception = exception;
    }

    private HttpBusinessException exception;

}
