package com.jinlong.uploadmodel.service.impl;

import com.jinlong.uploadmodel.dao.FirmTableDao;
import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.service.FirmTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FirmTableServiceImpl implements FirmTableService {
    @Autowired
    FirmTableDao firmTableDao;

    @Override
    public FirmTable getFirmTableById(Integer firmId) {
        FirmTable firmTable = firmTableDao.selectById(firmId);
        return firmTable;
    }
}
