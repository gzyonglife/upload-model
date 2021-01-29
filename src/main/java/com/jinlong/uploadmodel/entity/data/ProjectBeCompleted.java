package com.jinlong.uploadmodel.entity.data;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * project_be_completed
 * @author 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("project_be_completed")
public class ProjectBeCompleted implements Serializable {
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

    private static final long serialVersionUID = 1L;
}