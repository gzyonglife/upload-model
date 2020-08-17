package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.OverviewYearPlan;

import java.util.List;

public interface OverviewYearPlanService {
    /**
     * 获取年度项目计划总览
     *
     * @return
     */
    List<OverviewYearPlan> getOverviewYearPlan();
}
