package com.jinlong.uploadmodel.service.impl;

import com.jinlong.uploadmodel.dao.LoginLogTableDao;
import com.jinlong.uploadmodel.entity.data.LoginLogsTable;
import com.jinlong.uploadmodel.service.LoginLogService;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @description: LoginLogServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 18:20
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    LoginLogTableDao loginLogDao;

    @Override
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void log(Integer userId, String ipAddr) {
        if (userId != null && ipAddr != null) {
            LoginLogsTable logsTable = new LoginLogsTable();
            logsTable.setUserId(userId);
            logsTable.setLoginIp(ipAddr);
            logsTable.setLoginTime(new Date());
            loginLogDao.insert(logsTable);
        }
    }

    @Override
    public void error() {
        throw CustomExceptionEnum.TOKEN_ERROR.getException();
    }
}
