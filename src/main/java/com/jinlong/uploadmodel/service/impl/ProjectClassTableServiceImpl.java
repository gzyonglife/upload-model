package com.jinlong.uploadmodel.service.impl;

import com.jinlong.uploadmodel.dao.ProjectClassTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectClassTable;
import com.jinlong.uploadmodel.service.ProjectClassTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectClassTableServiceImpl implements ProjectClassTableService {
    @Autowired
    ProjectClassTableDao projectClassTable;
    @Override
    public ProjectClassTable getProjectClassTableById(Integer projectClassTableId) {
        return projectClassTable.selectById(projectClassTableId);
    }
}
