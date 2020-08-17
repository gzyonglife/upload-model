package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * overview_year_plan
 * @author 
 */
@Data
@TableName("overview_year_plan")
public class OverviewYearPlan implements Serializable {
    private static final long serialVersionUID = 1017403568607464619L;
    /**
     * 年度项目总览表id
     */
    @TableId(type = IdType.AUTO)
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

}