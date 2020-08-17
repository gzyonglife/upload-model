package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.OverviewAdministrativePlanProjectDao;
import com.jinlong.uploadmodel.entity.data.OverviewAdministrativePlanProject;
import com.jinlong.uploadmodel.service.OverviewAdministrativePlanProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class OverviewAdministrativePlanProjectServiceImpl implements OverviewAdministrativePlanProjectService {
    @Autowired
    OverviewAdministrativePlanProjectDao overviewAdministrativePlanProjectDao;

    @Override
    public List<OverviewAdministrativePlanProject> getOverviewAdministrativePlanProject() {
        return overviewAdministrativePlanProjectDao.selectList(new QueryWrapper<>());
    }
}
