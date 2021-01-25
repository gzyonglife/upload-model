package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;

import java.util.List;


public interface FirmTableService {

    /**
     * 根据单位id查询
     *
     * @param firmId
     * @return
     */
    FirmTable getFirmTableById(Integer firmId);

    /**
     * 模糊查询单位信息
     * @param firmName
     * @param current
     * @param size
     * @return
     */
    PageVo<FirmTable> getFirmTableByLimt(String firmName, Integer current, Integer size);

    /**
     * 修改单位信息
     * @param firmTable
     * @return
     */
    Boolean updateFirmTable(FirmTable firmTable);

    /**
     * 添加单位信息
     * @param firmTable
     * @return
     */
    Boolean addFirmTable(FirmTable firmTable);

    /**
     * 批量删除单位
     * @param list
     * @return
     */
    Boolean delFirmTable(List list);
}
