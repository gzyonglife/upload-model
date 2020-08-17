package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * administrative_table
 * @author 
 */
@Data
@TableName("administrative_table")
public class AdministrativeTable implements Serializable {
    private static final long serialVersionUID = 4142572489736608947L;
    /**
     * 青浦区行政区域表id
     */
    @TableId(type = IdType.AUTO)
    private Integer administrativeTableId;

    /**
     * 行政区域名称
     */
    private String administrativeName;

}