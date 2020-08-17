package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.StatisticsProjectTypeDao;
import com.jinlong.uploadmodel.entity.data.StatisticsProjectType;
import com.jinlong.uploadmodel.service.StatisticsProjectTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class StatisticsProjectTypeServiceImpl implements StatisticsProjectTypeService {
    @Autowired
    StatisticsProjectTypeDao statisticsProjectTypeDao;

    @Override
    public List<StatisticsProjectType> getStatisticsProjectType() {
        return statisticsProjectTypeDao.selectList(new QueryWrapper<>());
    }
}
