package com.jinlong.uploadmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinlong.uploadmodel.entity.data.ProjectZoneTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: ProjectZoneTableDao
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/8 15:16
 */
@Mapper
@Repository
public interface ProjectZoneTableDao extends BaseMapper<ProjectZoneTable> {
}