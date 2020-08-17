package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * project_details_table
 *
 * @author
 */
@Data
@TableName("project_details_table")
public class ProjectDetailsTable implements Serializable {
    private static final long serialVersionUID = 2952542567085315613L;
    /**
     * 项目详情id
     */

    private Integer projectDetailsId;
    /**
     * 项目id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 创建时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 项目备注
     */
    private String projectNote;

    /**
     * 项目所在分类id
     */
    private Integer projectCategoryId;

    /**
     * 项目年度编号
     */
    private String itemNumber;

    /**
     * 建设单位id
     */
    private Integer constructionFirmId;

    /**
     * 代建单位id
     */
    private Integer agentConstructionFirmId;

    /**
     * 配合单位id
     */
    private Integer cooperateFirmId;

    /**
     * 父级项目id
     */
    private Integer projectParent;

    /**
     * 项目计划id
     */
    private Integer projectPlanId;

    /**
     * 项目计划实施状况id
     */
    private Integer projectPlanPracticalId;

    /**
     * 项目所属用户id
     */
    private Integer userId;

    /**
     * 续建项目id
     */
    private Integer continueProjectId;

}