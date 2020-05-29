package com.jinlong.uploadmodel.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user_table
 *
 * @author
 */
@Data
@TableName("user_table")
public class UserTable implements Serializable {


    private static final long serialVersionUID = -515761094065624276L;
    /**
     * 用户id，主键
     */
    @TableId
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户登录名
     */
    private String loginName;

    /**
     * 用户登录密码
     */
    private String loginPassword;

    /**
     * 创建者id
     */
    private Integer creatorId;

    /**
     * 创建时间戳
     */
    private Date createTime;

    /**
     * 用户存储空间地址
     */
    private String userZone;

    /**
     * 用户权限id
     */
    private Integer roleId;

}