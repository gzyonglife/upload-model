package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * ProjectModelTypeVo
 *
 * @author
 */
@Data
public class ProjectModelTypeVo {
    /**
     * 模型类型表id
     */
    private Integer projectModelTypeId;

    /**
     * 模型类型
     */
    @NotEmpty(message = "模型类型不可为空")
    private String projectModelType;

    /**
     * 是否为文件夹
     */
    private Boolean isFolder;
}