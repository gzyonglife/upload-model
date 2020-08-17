package com.jinlong.uploadmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinlong.uploadmodel.entity.data.OverviewYearPlan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OverviewYearPlanDao extends BaseMapper<OverviewYearPlan> {

}