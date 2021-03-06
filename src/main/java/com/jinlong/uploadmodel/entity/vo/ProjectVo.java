package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @description: ProjectVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 13:19
 */
@Data
public class ProjectVo {
    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    @NotEmpty(message = "projectName是必须的")
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
     * 父级项目名称
     */
    private String projectParentName;
    /**
     * 项目类型id
     */
    @NotNull(message = "projectCategoryId是必须的")
    private Integer projectCategoryId;

    /**
     * 创建时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    /**
     * 项目类型名称
     */
    private String projectClassTableName;

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

    /**
     *  青浦区行政区域名称
     */
    private List<String> administrativeTableName;
}