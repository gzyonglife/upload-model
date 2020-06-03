package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @description: ProjectVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 13:19
 */
@Data
public class ProjectVo {
    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    @NotEmpty(message = "projectName是必须的")
    private String projectName;

    /**
     * 项目所属用户id
     */
    private Integer userId;

    /**
     * 项目详情id
     */
    private Integer projectDetailsId;
    /**
     * 父级项目id
     */
    private Integer projectParent;

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
     * 项目年度编号
     */
    @NotEmpty(message = "itemNumber是必须的")
    private String itemNumber;
}