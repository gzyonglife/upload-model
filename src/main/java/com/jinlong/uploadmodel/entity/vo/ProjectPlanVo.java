package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: ProjectPlanVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 18:34
 */
@Data
public class ProjectPlanVo {
    /**
     * 项目计划id
     */
    private Integer projectPlanId;
    /**
     * 项目id
     */
    @NotNull(message = "projectId是必须的")
    private Integer projectId;
    /**
     * 计划年份
     */

    @NotNull(message = "projectPlanYear是必须的")
    private Date projectPlanYear;

    /**
     * 开工日期
     */
    private Date projectPlanExpectStartTime;

    /**
     * 竣工日期
     */
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
    @NotNull(message = "planType是必须的")
    private Boolean planType;

    /**
     * 重点关注
     */
    private Integer isFocus;
    /**
     * 项目备注
     */
    private String projectNote;

    /**
     * 项目年度编号
     */
    @NotEmpty(message = "itemNumber是必须的")
    private String itemNumber;

}
