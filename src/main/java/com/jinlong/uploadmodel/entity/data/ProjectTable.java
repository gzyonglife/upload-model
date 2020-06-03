package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * project_table
 *
 * @author
 */
@Data
@TableName("project_table")
public class ProjectTable implements Serializable {
    private static final long serialVersionUID = -3619639758420748705L;
    /**
     * 项目id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目所属用户id
     */
    private Integer userId;

    /**
     * 项目详情id
     */
    private Integer projectDetailsId;

    /**
     * 父级项目id
     */
    private Integer projectParent;

    /**
     * 创建时间戳
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 项目备注
     */
    private String projectNote;

    /**
     * 项目年度编号
     */
    private String itemNumber;

}