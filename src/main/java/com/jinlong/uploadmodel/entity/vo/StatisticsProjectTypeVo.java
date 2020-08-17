package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

@Data
public class StatisticsProjectTypeVo{

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

    /**
     * 项目类型名称
     */
    private String project_category_name;

}
