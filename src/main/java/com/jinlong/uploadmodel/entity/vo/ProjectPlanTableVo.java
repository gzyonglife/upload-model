package com.jinlong.uploadmodel.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectPlanTableVo {
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
     * 项目名称
     */
    private String projectName;
}
