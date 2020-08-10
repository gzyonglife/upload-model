package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * project_category_table
 *
 * @author
 */
@Data
@TableName("project_category_table")
public class ProjectCategoryTable implements Serializable {
    private static final long serialVersionUID = -6820776223259593164L;
    /**
     * 项目分类id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectCategoryId;

    /**
     * 项目名称
     */
    private String projectCategoryName;

    /**
     * 父级分类id
     */
    private Integer projectParentCategoryId;

}