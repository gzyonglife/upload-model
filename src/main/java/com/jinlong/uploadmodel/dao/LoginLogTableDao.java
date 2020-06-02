package com.jinlong.uploadmodel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinlong.uploadmodel.entity.data.LoginLogsTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description: LoginLogTableDao
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 18:08
 */
@Mapper
@Repository
public interface LoginLogTableDao extends BaseMapper<LoginLogsTable> {
}
