package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.AdministrativeTableBindingAdministrativeTable;


public interface AdministrativeTableBindingAdministrativeTableService {
    /**
     * 青浦区区域计划与行政区域绑定表查询
     *
     * @param administrativeTableBindingAdministrativeTableId
     * @return
     */
    AdministrativeTableBindingAdministrativeTable getAdministrativeTableBindingAdministrativeTable(Integer administrativeTableBindingAdministrativeTableId);
}
