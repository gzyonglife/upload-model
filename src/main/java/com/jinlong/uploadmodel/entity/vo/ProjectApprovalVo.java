package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

/**
 * @Author gzy
 * @Date 2021/1/28 15:40
 * @Version 1.0
 */
@Data
public class ProjectApprovalVo {
    /**
     * 审批id
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 建设工程规划类许可审批结果
     */
    private Integer build;

    /**
     * 施工许可（开工备案）
     */
    private Integer construction;

    /**
     * 合同信息报送
     */
    private Integer contract;

    /**
     * 取水许可审批结果
     */
    private Integer waterIntake;

    /**
     * 水土保持方案审批结果
     */
    private Integer soilAndWater;

    /**
     * 洪水影响评价等涉水事项审批结果
     */
    private Integer flood;

    /**
     *  项目名称
     */
    private String projectName;
}
