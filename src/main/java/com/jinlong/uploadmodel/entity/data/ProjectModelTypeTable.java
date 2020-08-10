package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * project_model_type_table
 *
 * @author
 */
@Data
@TableName("project_model_type_table")
public class ProjectModelTypeTable implements Serializable {
    private static final long serialVersionUID = -1626378530291278745L;
    /**
     * 模型类型表id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectModelTypeId;

    /**
     * 模型类型
     */
    private String projectModelType;
    /**
     * 是否为文件夹
     */
    private Boolean isFolder;

}