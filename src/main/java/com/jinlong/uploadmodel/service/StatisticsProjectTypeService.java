package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.StatisticsProjectType;

import java.util.List;

/**
 * @description: StatisticsProjectTypeService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/8/12 11:31
 */
public interface StatisticsProjectTypeService {
    /**
     * 获取项目类型统计表
     *
     * @return
     */
    List<StatisticsProjectType> getStatisticsProjectType();
}
