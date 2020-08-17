package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * firm_table
 * @author 
 */
@Data
@TableName("firm_table")
public class FirmTable implements Serializable {
    private static final long serialVersionUID = 1306226740453154323L;
    /**
     * 单位id
     */
    @TableId(type = IdType.AUTO)
    private Integer firmId;

    /**
     * 单位名称
     */
    private String firmName;

    /**
     * 联系方式
     */
    private String firmPhone;
}