package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.OverviewProjectDao;
import com.jinlong.uploadmodel.entity.data.OverviewProject;
import com.jinlong.uploadmodel.service.OverviewProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class OverviewProjectServiceImpl implements OverviewProjectService {
    @Autowired
    OverviewProjectDao overviewProjectDao;

    @Override
    public List<OverviewProject> getOverviewProject() {
        return overviewProjectDao.selectList(new QueryWrapper<>());
    }
}
