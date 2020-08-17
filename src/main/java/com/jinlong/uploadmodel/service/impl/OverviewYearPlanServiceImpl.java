package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.OverviewYearPlanDao;
import com.jinlong.uploadmodel.entity.data.OverviewYearPlan;
import com.jinlong.uploadmodel.service.OverviewYearPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class OverviewYearPlanServiceImpl implements OverviewYearPlanService {
    @Autowired
    OverviewYearPlanDao overviewYearPlanDao;

    @Override
    public List<OverviewYearPlan> getOverviewYearPlan() {
        return overviewYearPlanDao.selectList(new QueryWrapper<>());
    }
}
