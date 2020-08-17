package com.jinlong.uploadmodel.service.impl;

import com.jinlong.uploadmodel.dao.AdministrativeTableBindingAdministrativeTableDao;
import com.jinlong.uploadmodel.entity.data.AdministrativeTableBindingAdministrativeTable;
import com.jinlong.uploadmodel.service.AdministrativeTableBindingAdministrativeTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AdministrativeTableBindingAdministrativeTableServiceImpl implements AdministrativeTableBindingAdministrativeTableService {
    @Autowired
    AdministrativeTableBindingAdministrativeTableDao administrativeTableBindingAdministrativeTableDao;
    @Override
    public AdministrativeTableBindingAdministrativeTable getAdministrativeTableBindingAdministrativeTable(Integer administrativeTableBindingAdministrativeTableId) {
        return administrativeTableBindingAdministrativeTableDao.selectById(administrativeTableBindingAdministrativeTableId);
    }
}
