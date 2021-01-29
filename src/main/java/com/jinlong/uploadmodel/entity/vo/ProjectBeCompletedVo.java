package com.jinlong.uploadmodel.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author gzy
 * @Date 2021/1/29 9:18
 * @Version 1.0
 */
@Data
public class ProjectBeCompletedVo {
    /**
     * 项目竣工信息id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 备案编码
     */
    private String recordCode;

    /**
     * 报建编号
     */
    private String applicationNo;

    /**
     * 建设单位id
     */
    private Integer constructionUnit;

    /**
     * 工程地址
     */
    private String projectAddress;

    /**
     * 备案日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordDate;

    /**
     * 备案机关
     */
    private String filingAuthority;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 单位名称
     */
    private String firmName;
}
