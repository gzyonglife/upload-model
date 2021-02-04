package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * project_approval
 * @author
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("project_approval")
public class ProjectApproval implements Serializable {
    /**
     * 审批id
     */
    @TableId(type = IdType.AUTO)
    @NotNull(message = "id不能为空")
    private Integer id;

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空")
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

    private static final long serialVersionUID = 1L;
}