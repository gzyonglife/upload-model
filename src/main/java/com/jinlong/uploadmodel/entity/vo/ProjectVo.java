package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
     * 项目详情id
     */
    private Integer projectDetailsId;
    /**
     * 父级项目id
     */
    private Integer projectParent;
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
     * 重点关注
     */
    private Integer isFocus;
    /**
     * 项目备注
     */
    private String projectNote;

    /**
     * 项目年度编号
     */
    @NotEmpty(message = "itemNumber是必须的")
    private String itemNumber;

    /**
     * 项目类型名称
     */
    private String projectClassTableName;

    /**
     * 项目位置经纬度
     */
    private String LongitudeAndLatitude;
    /**
     * 项目视角
     */
    private String projectView;
    /**
     * 缩略图地址
     */
    private String imgUrl;
}