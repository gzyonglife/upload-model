package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * administrative_plan
 * @author 
 */
@Data
@TableName("administrative_plan")
public class AdministrativePlan implements Serializable {
    private static final long serialVersionUID = 6686575329344279205L;
    /**
     * 青浦区行政区域计划表id
     */
    @TableId(type = IdType.AUTO)
    private Integer administrativePlanId;

    /**
     * 计划名称
     */
    private String administrativePlanName;

}