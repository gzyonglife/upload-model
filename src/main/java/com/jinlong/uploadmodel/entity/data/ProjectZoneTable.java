package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * project_zone
 *
 * @author
 */
@Data
@TableName("project_zone")
public class ProjectZoneTable implements Serializable {
    private static final long serialVersionUID = -88386476299942843L;
    /**
     * 项目文件空间地址id
     */
    @TableId(type = IdType.AUTO)
    private Integer projectZoneId;

    /**
     * 项目文件空间地址
     */
    private String projectZonePath;
    /**
     * 项目访问端口号
     */
    private String projectZonePort;
}