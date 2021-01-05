package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * project_plan_table
 *
 * @author
 */
@Data
@TableName("project_plan_table")
public class ProjectPlanTable implements Serializable {
    private static final long serialVersionUID = 8367204760974779138L;
    /**
     * 项目计划id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectPlanId;

    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 计划年份
     */
    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date projectPlanYear;

    /**
     * 开工日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectPlanExpectStartTime;

    /**
     * 竣工日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectPlanExpectEndTime;

    /**
     * 总投资额
     */
    private Double projectPlanInvestTotal;

    /**
     * 完成额
     */
    private Double projectPlanInvestFinish;

    /**
     * 一月目标
     */
    private String projectPlanJanuary;

    /**
     * 二月目标
     */
    private String projectPlanFebruary;

    /**
     * 三月目标
     */
    private String projectPlanMarch;

    /**
     * 四月目标
     */
    private String projectPlanApril;

    /**
     * 五月目标
     */
    private String projectPlanMay;

    /**
     * 六月目标
     */
    private String projectPlanJune;

    /**
     * 七月目标
     */
    private String projectPlanJuly;

    /**
     * 八月目标
     */
    private String projectPlanAugust;

    /**
     * 九月目标
     */
    private String projectPlanSeptember;

    /**
     * 十月目标
     */
    private String projectPlanOctober;

    /**
     * 十一月目标
     */
    private String projectPlanNovember;

    /**
     * 十二月阶目标
     */
    private String projectPlanDecember;

    /**
     * 是否为计划，或者为实施
     */
    private Boolean planType;

    /**
     * 项目备注
     */
    private String projectNote;

    /**
     * 项目年度编号
     */
    private String itemNumber;
    /**
     * 重点关注
     */

}