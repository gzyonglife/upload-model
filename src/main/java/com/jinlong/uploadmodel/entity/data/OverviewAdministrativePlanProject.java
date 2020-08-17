package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * overview_administrative_plan_project
 * @author 
 */
@Data
public class OverviewAdministrativePlanProject implements Serializable {
    private static final long serialVersionUID = 6194155936112427652L;
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

}