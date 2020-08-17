package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.AdministrativeTable;

public interface AdministrativeTableService {
    /**
     * 青浦区行政区域表查询
     *
     * @param id
     * @return
     */
    AdministrativeTable getAdministrativeTableId(Integer id);
}
