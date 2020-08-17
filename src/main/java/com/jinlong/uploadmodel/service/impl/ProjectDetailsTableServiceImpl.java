package com.jinlong.uploadmodel.service.impl;

import com.jinlong.uploadmodel.dao.ProjectDetailsTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectDetailsTable;
import com.jinlong.uploadmodel.service.ProjectDetailsTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ProjectDetailsTableServiceImpl implements ProjectDetailsTableService {
    @Autowired
    ProjectDetailsTableDao projectDetailsTableDao;
    @Override
    public ProjectDetailsTable getProjectDetailsTableById(Integer getProjectDetailsTableId) {
        return projectDetailsTableDao.selectById(getProjectDetailsTableId);
    }
}
