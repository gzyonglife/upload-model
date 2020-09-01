package com.jinlong.uploadmodel.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jinlong.uploadmodel.entity.data.FirmTable;
import lombok.Data;

import java.util.Date;

/**
 * @author jinlong
 */
@Data
public class ProjectsVo {
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目分类名称
     */
    private String cateGoryName;
    /**
     * 是否为重点关注项目
     */
    private Boolean focus;
    /**
     * 项目计划开工日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectTimeOpen;
    /**
     * 项目计划竣工日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectTimeDown;
    /**
     * 项目实际开工日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectTimeOpens;
    /**
     * 项目实际竣工日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date projectTimeDowns;
    /**
     * 计划总投资
     */
    private Double kilometers;
    /**
     * 计划本年完成额
     */
    private Double kilometersYear;
    /**
     * 截至本月已用额度
     */
    private Double kilometersMonth;
    /**
     * 建设单位
     */
    private FirmTable build;
    /**
     * 代建单位
     */
    private FirmTable agentConstruction;
    /**
     * 配合单位
     */
    private FirmTable coordination;
    /**
     * 本月目标计划
     */
    private String target;
    /**
     * 本月实际进度
     */
    private String actual;
    /**
     * 项目位置经纬度
     */
    private String LongitudeAndLatitude;
}
