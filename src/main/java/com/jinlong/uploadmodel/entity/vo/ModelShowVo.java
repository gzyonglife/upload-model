package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description: ModelShowVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/16 14:16
 */
@Data
public class ModelShowVo {
    /**
     * 模型id
     */
    private Integer projectModelId;
    /**
     * 模型相对项目的路径
     */
    private String projectModelPath;
    /**
     * 模型名
     */
    private String projectModelName;
    /**
     * 模型对应的项目时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date projectModelTime;
    /**
     * 模型类型名称
     */
    private String projectModelType;
    /**
     * 模型视角
     */
    private String projectModelView;
    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 模型访问端口
     */
    private String port;
    /**
     * 访问根路径地址
     */
    private String pathZone;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 模型类型
     */
    private Integer projectModelTypeId;
}
