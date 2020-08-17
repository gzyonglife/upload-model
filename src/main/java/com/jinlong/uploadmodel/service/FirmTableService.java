package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.FirmTable;

public interface FirmTableService {

    /**
     * 根据单位id查询
     *
     * @param firmId
     * @return
     */
    FirmTable getFirmTableById(Integer firmId);
}
