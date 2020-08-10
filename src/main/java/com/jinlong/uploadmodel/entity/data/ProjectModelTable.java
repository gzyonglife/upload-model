package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * project_model_table
 *
 * @author
 */
@Data
@TableName("project_model_table")
public class ProjectModelTable implements Serializable {
    private static final long serialVersionUID = 7110764745826257908L;
    /**
     * 项目模型表id
     */
    @TableId(type = IdType.AUTO)
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
     * 项目id
     */
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
    private Date projectModelTime;

    /**
     * 模型类型
     */
    private Integer projectModelTypeId;
    /**
     * 模型视角
     */
    private String projectModelView;

}