package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;

/**
 * @description: ProjectService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 13:20
 */
public interface ProjectService {
    /**
     * 获取项目列表
     *
     * @param pageVo
     * @param user
     * @return
     */
    PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo, UserDetails user);

    /**
     * 添加新项目
     *
     * @param projectVo
     * @param user
     * @return
     */
    boolean addProject(ProjectVo projectVo, UserDetails user);
}
