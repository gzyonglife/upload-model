package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * project_model_table
 *
 * @author
 */
@Data
public class ProjectModelVo {
    /**
     * 项目模型表id
     */
    private Long projectModelId;

    /**
     * 模型相对项目的路径
     */
    private String projectModelPath;
    /**
     * 模型名
     */
    @NotEmpty(message = "模型名不能为空")
    private String projectModelName;

    /**
     * 项目id
     */
    @NotNull(message = "项目id不能为空")
    private Integer projectId;

    /**
     * 用户id
     */
    private Integer createUserId;

    /**
     * 上传时间
     */
    private Date createTime;

    /**
     * 模型对应的项目时间
     */

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotNull(message = "模型对应的项目时间不能为空")
    private Date projectModelTime;

    /**
     * 模型类型
     */
    @NotNull(message = "模型类型d不能为空")
    private Integer projectModelTypeId;

    /**
     * 模型视角
     */
    private String projectModelView;

}