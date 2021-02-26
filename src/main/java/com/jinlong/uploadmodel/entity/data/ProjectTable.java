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
     * 父级项目id
     */
    private Integer projectParent;
    /**
     * 项目文件空间地址id
     */
    private Integer projectZoneId;
    /**
     * 项目类型id
     */
    private Integer projectCategoryId;
    /**
     * 创建时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 项目位置经纬度
     */
    private String longitudeAndLatitude;

    /**
     * 缩略图地址
     */
    private String imgUrl;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     *  青浦区行政区域表id
     */
    private String administrativeTableId;
}