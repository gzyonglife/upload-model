package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectDetailsTable;

public interface ProjectDetailsTableService {
    /**
     * 根据项目id查询项目详情
     */
    ProjectDetailsTable getProjectDetailsTableById(Integer getProjectDetailsTableId);
}
