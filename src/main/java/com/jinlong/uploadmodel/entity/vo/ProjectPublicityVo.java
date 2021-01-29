package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

/**
 * @Author gzy
 * @Date 2021/1/29 10:17
 * @Version 1.0
 */
@Data
public class ProjectPublicityVo {
    /**
     * 项目公示信息id
     */
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 征地告知书
     */
    private Integer landRequisitionNotice;

    /**
     * 征地批文
     */
    private Integer landAcquisitionApproval;

    /**
     * 一书四方案
     */
    private Integer fourPlans;

    /**
     * 勘察单位信息
     */
    private Integer survey;

    /**
     * 设计单位信息
     */
    private Integer design;

    /**
     * 施工单位信息
     */
    private Integer construction;

    /**
     * 监理单位信息
     */
    private Integer supervisor;

    /**
     * 施工期环境保护措施及落实情况
     */
    private Integer protectiveMeasures;

    /**
     * 项目质量安全行政处罚情况
     */
    private Integer qualitySafety;

    /**
     * 中标候选人公示
     */
    private Integer winningTheBid;

    /**
     * 中标结果公示
     */
    private Integer resultTheBid;

    /**
     * 合同信息
     */
    private Integer contractInformation;

    /**
     * 招投标违法处罚信息
     */
    private Integer bidding;

    /**
     * 项目名称
     */
    private String projectName;
}
