package com.jinlong.uploadmodel.service.impl;

import com.jinlong.uploadmodel.dao.AdministrativePlanDao;
import com.jinlong.uploadmodel.entity.data.AdministrativePlan;
import com.jinlong.uploadmodel.service.AdministrativePlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdministrativePlanServiceImpl implements AdministrativePlanService {
    @Autowired
    AdministrativePlanDao administrativePlanDao;

    @Override
    public AdministrativePlan getAdministrativePlanById(Integer administrativePlanId) {
        return administrativePlanDao.selectById(administrativePlanId);
    }
}
