package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * project_class_table
 * @author 
 */
@Data
@TableName("project_class_table")
public class ProjectClassTable implements Serializable {
    private static final long serialVersionUID = 5812289711141813236L;
    /**
     * 项目种类表id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectClassTableId;

    /**
     * 项目种类名称
     */
    private String projectClassTableName;

}