package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.AdministrativePlan;

public interface AdministrativePlanService {
    /**
     * 计划表查询
     *
     * @param administrativePlanId
     * @return
     */
    AdministrativePlan getAdministrativePlanById(Integer administrativePlanId);
}
