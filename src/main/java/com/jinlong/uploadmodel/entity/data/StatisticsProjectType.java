package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;
import lombok.Data;

/**
 * statistics_project_type
 * @author 
 */
@Data
public class StatisticsProjectType implements Serializable {
    private static final long serialVersionUID = 2011802266115585412L;
    /**
     * 项目类型统计表id
     */
    private Integer statisticsProjectTypeId;

    /**
     * 项目分类id
     */
    private Integer projectCategoryId;

    /**
     * 项目类型统计数量
     */
    private Integer statisticsProjectTypeNull;

}