package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectClassTable;

public interface ProjectClassTableService {
    /**
     * 根据项目种类id获取项目种类
     *
     * @param projectClassTableId
     * @return
     */
    ProjectClassTable getProjectClassTableById(Integer projectClassTableId);
}
