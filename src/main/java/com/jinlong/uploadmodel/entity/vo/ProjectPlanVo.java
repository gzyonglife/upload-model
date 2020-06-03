package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
     * 项目详情id
     */
    private Integer projectDetailsId;
    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目在用户存储空间的地址
     */
    private String projectZone;

    /**
     * 创建时间戳
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
