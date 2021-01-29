package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * project_publicity
 * @author 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("project_publicity")
public class ProjectPublicity implements Serializable {
    /**
     * 项目公示信息id
     */
    @TableId(type = IdType.AUTO)
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

    private static final long serialVersionUID = 1L;
}