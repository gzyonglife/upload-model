package com.jinlong.uploadmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author jinlong
 */
@Mapper
@Repository
public interface ProjectPlanTableDao extends BaseMapper<ProjectPlanTable> {
}