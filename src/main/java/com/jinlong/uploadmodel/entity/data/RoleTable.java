package com.jinlong.uploadmodel.entity.data;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * role_table
 *
 * @author
 */
@Data
@TableName("role_table")
public class RoleTable implements Serializable {
    private static final long serialVersionUID = -2322014478068634121L;
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色权限
     */
    private String roleAccess;

}