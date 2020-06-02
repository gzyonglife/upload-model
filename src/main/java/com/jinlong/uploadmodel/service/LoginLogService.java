package com.jinlong.uploadmodel.service;

/**
 * @description: LoginLogService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 18:19
 */
public interface LoginLogService {
    /**
     * 添加用户登录日志
     * @param userId
     * @param ipAddr
     */
    void log(Integer userId, String ipAddr);

    void error();
}
