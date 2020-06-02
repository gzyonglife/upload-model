package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * login_logs_table
 *
 * @author
 */
@Data
@TableName("login_logs_table")
public class LoginLogsTable implements Serializable {
    private static final long serialVersionUID = 7082541367418057545L;
    /**
     * 登录日志id
     */
    @TableId(type = IdType.AUTO)
    private Long logsId;

    /**
     * 登录用户id
     */
    private Integer userId;

    /**
     * 登录时间戳
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loginTime;

    /**
     * 登录地址ip
     */
    private String loginIp;

}