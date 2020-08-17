package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * administrative_table_binding_administrative_table
 * @author 
 */
@Data
@TableName("administrative_table_binding_administrative_table")
public class AdministrativeTableBindingAdministrativeTable implements Serializable {
    private static final long serialVersionUID = -721968445639818065L;
    /**
     * 青浦区区域计划与行政区域绑定表id
     */
    @TableId(type = IdType.AUTO)
    private Integer administrativeTableBindingAdministrativeTableId;

    /**
     * 青浦区行政区域表id
     */
    private Integer administrativeTableId;

    /**
     * 青浦区行政区域计划表id
     */
    private Integer administrativePlanId;

}