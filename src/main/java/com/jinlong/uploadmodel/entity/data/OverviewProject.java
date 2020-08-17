package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * overview_project
 * @author 
 */
@Data
@TableName("overview_project")
public class OverviewProject implements Serializable {
    private static final long serialVersionUID = -4750765503276870833L;
    /**
     * 项目总体情况表id
     */
    @TableId(type = IdType.AUTO)
    private Integer overviewProjectId;

    /**
     * 项目总数量
     */
    private Integer overviewProjectAllNum;

    /**
     * 项目总资金
     */
    private Integer overviewProjectAllMoney;

    /**
     * 项目已开工总数量
     */
    private Integer overviewProjectStartsNum;

    /**
     * 项目已使用资金
     */
    private Integer overviewProjectUseMoney;

}