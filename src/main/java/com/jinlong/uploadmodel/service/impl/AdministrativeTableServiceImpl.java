package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.AdministrativeTableDao;
import com.jinlong.uploadmodel.entity.data.AdministrativeTable;
import com.jinlong.uploadmodel.service.AdministrativeTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdministrativeTableServiceImpl implements AdministrativeTableService {
    @Autowired
    AdministrativeTableDao administrative;

    @Override
    public AdministrativeTable getAdministrativeTableId(Integer administrativeTableId) {
        return administrative.selectById(administrativeTableId);
    }

    @Override
    public List<AdministrativeTable> getAdministrativeTable() {
        return administrative.selectList(new QueryWrapper<>());
    }
}
