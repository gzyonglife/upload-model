package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;


public interface FirmTableService {

    /**
     * 根据单位id查询
     *
     * @param firmId
     * @return
     */
    FirmTable getFirmTableById(Integer firmId);

    PageVo<FirmTable> getFirmTableByLimt(String firmName, Integer current, Integer size);
}
