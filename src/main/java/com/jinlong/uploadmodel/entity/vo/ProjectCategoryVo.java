package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description: ProjectCategoryVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/9 15:15
 */
@Data
public class ProjectCategoryVo {
    /**
     * 项目分类id
     */
    private Integer projectCategoryId;

    /**
     * 项目名称
     */
    @NotEmpty(message = "projectCategoryName不可为空")
    private String projectCategoryName;

    /**
     * 父级分类id
     */
    private Integer projectParentCategoryId;

}
