package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.AdministrativeTable;

import java.util.List;

public interface AdministrativeTableService {
    /**
     * 青浦区行政区域表查询
     *
     * @param id
     * @return
     */
    AdministrativeTable getAdministrativeTableId(Integer id);

    /**
     * 获取行政区域表列表
     * @return
     */
    List<AdministrativeTable> getAdministrativeTable();
}
