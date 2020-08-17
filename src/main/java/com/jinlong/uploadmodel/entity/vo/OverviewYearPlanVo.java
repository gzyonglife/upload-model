package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

@Data
public class OverviewYearPlanVo {
    /**
     * 年度项目总览表id
     */
    private Integer overviewYearPlanId;

    /**
     * 年度项目种类id
     */
    private Integer projectClassTableId;


    /**
     * 年内计划项目
     */
    private Integer overviewYearPlan;
    /**
     * 年内计划开工数量
     */
    private Integer overviewYearPlanStarts;
    /**
     * 年内计划竣工数量
     */
    private Integer overviewYearPlanComplete;
    /**
     * 年度项目种类名称
     */
    private String projectClassTableName;
}
