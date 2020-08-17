package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

@Data
public class OverviewAdministrativePlanProjectVo {

    /**
     * 区域计划项目数量表id
     */
    private Integer overviewAdministrativePlanProjectId;

    /**
     * 青浦区区域计划与行政区域绑定表id
     */
    private Integer administrativeTableBindingAdministrativeTableId;

    /**
     * 计划在该区域的项目数
     */
    private Integer overviewAdministrativePlanProjectNum;

    /**
     * 区域名称
     */
    private String administrativeTableBindingAdministrativeTableName;
    /**
     * 项目名称
     */
    private String administrativeName;
}
