package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.vo.RoleVo;

import java.util.List;

/**
 * @description: RoleService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/2 19:41
 */
public interface RoleService {
    /**
     * 获取权限列表
     * @return
     */
    List<RoleVo> getRoleList();
}
