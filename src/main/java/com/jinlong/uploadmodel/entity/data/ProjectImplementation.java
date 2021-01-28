package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * project_implementation
 * @author 
 */
@Data
@TableName("project_implementation")
public class ProjectImplementation implements Serializable {
    /**
     * 项目实施表id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 实施时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date implementationDate;

    /**
     * 实施具体内容
     */
    private String implementationText;

    /**
     * 完成额
     */
    private Double implementationMoney;

    private static final long serialVersionUID = 1L;
}