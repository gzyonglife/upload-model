package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.OverviewProject;

import java.util.List;

public interface OverviewProjectService {
    /**
     * 项目总体情况获取
     *
     * @return
     */
    List<OverviewProject> getOverviewProject();
}
