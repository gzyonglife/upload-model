package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.service.ProjectService;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: ProjectServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 13:21
 */
@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectTableDao projectDao;

    /**
     * 获取项目列表，除超级管理员外，其他人仅可获取属于自己的项目
     *
     * @param pageVo
     * @param user
     * @return
     */
    @Override
    public PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo, UserDetails user) {
        Page<ProjectTable> tablePage;
        // 判断是不是超级管理员
        if (user.hasRole("SUPERADMIN")) {
            // 是超级管理员，获取所有项目
            tablePage = projectDao.selectPage(
                    new Page<>(pageVo.getCurrent(), pageVo.getSize())
                    , new QueryWrapper<>());
        } else {
            // 非超级管理员，仅获取属于自己的项目
            tablePage = projectDao.selectPage(
                    new Page<>(pageVo.getCurrent(), pageVo.getSize())
                    , new QueryWrapper<ProjectTable>().eq("user_id", user.getId()));
        }
       return PageVo.createPageVoOfPage(tablePage,ProjectVo.class);
    }

    /**
     * 添加新项目，超级管理员可以给其他用户添加，其他用户尽可以给自己添加
     *
     * @param projectVo
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addProject(ProjectVo projectVo, UserDetails user) {

        // 判断是否是给自己添加项目
        if (projectVo.getUserId() == null)
            // 给自己添加新项目
            projectVo.setUserId(user.getId());
        return projectDao.insert(BeanBeanHelpUtils.copyProperties(projectVo, ProjectTable.class)) == 1;
    }
}
