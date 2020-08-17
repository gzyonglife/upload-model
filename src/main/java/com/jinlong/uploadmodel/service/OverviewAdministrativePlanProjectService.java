package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.OverviewAdministrativePlanProject;

import java.util.List;

public interface OverviewAdministrativePlanProjectService {
    /**
     * 区域计划项目数量获取
     *
     * @return
     */
    List<OverviewAdministrativePlanProject> getOverviewAdministrativePlanProject();
}
