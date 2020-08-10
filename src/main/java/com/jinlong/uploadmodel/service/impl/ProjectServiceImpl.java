package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.config.selector.ProjectZoneSelector;
import com.jinlong.uploadmodel.dao.ProjectDetailsTableDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectDetailsTable;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.service.ProjectService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    ProjectDetailsTableDao projectDetailsDao;

    @Autowired
    ProjectZoneSelector projectZoneSelector;


    /**
     * 获取项目列表
     *
     * @param pageVo
     * @return
     */
    @Override
    public PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo) {
        Page<ProjectTable> tablePage = projectDao.selectPage(
                new Page<>(pageVo.getCurrent(), pageVo.getSize())
                , new QueryWrapper<>());
        return PageVo.createPageVoOfPage(tablePage, ProjectVo.class);
    }

    /**
     * 获取项目列表
     *
     * @param pageVo
     * @param userId
     * @return
     */
    @Override
    public PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo, Integer userId) {
        Page<ProjectTable> tablePage = projectDao.selectPage(
                new Page<>(pageVo.getCurrent(), pageVo.getSize())
                , new QueryWrapper<ProjectTable>().eq("user_id", userId));
        return PageVo.createPageVoOfPage(tablePage, ProjectVo.class);
    }

    /**
     * 根据项目分类id查询项目
     *
     * @param categoryId
     * @param userId
     * @return
     */
    @Override
    public List<ProjectVo> getProjectByCategoryId(Integer categoryId, Integer userId) {
        List<ProjectTable> projectTables = projectDao.selectList(
                new QueryWrapper<ProjectTable>().
                        eq("user_id", userId).
                        eq("project_category_id", categoryId));
        Assert.assertCollectionNotEmpty(projectTables, CustomExceptionEnum.SERVER_ERROR);
        List<ProjectVo> projectVos = BeanBeanHelpUtils.copyList(projectTables, ProjectVo.class);
        Assert.assertCollectionNotEmpty(projectVos, CustomExceptionEnum.SERVER_ERROR);
        return projectVos;
    }

    /**
     * 根据项目分类id查询项目
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<ProjectVo> getProjectByCategoryId(Integer categoryId) {
        List<ProjectTable> projectTables = projectDao.selectList(
                new QueryWrapper<ProjectTable>().eq("project_category_id", categoryId));
        Assert.assertCollectionNotEmpty(projectTables, CustomExceptionEnum.SERVER_ERROR);
        List<ProjectVo> projectVos = BeanBeanHelpUtils.copyList(projectTables, ProjectVo.class);
        Assert.assertCollectionNotEmpty(projectVos, CustomExceptionEnum.SERVER_ERROR);
        return projectVos;
    }

    /**
     * 添加新项目
     *
     * @param projectVo
     * @param userId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectVo addProject(ProjectVo projectVo, Integer userId) {
        ProjectTable entity = BeanBeanHelpUtils.copyProperties(projectVo, ProjectTable.class);
        entity.setUserId(userId);
        entity.setProjectZoneId(projectZoneSelector.getProjectZoneId());
        File file = new File(projectZoneSelector.getProjectZonePathById(entity.getProjectZoneId()) + "/" + entity.getProjectName());
        if (file.exists()) {
            log.warn("文件路径已经存在，请检查存储空间");
            // TODO 抛出文件路径已经存在异常
            throw CustomExceptionEnum.SERVER_ERROR.getException();
        } else {
            file.mkdir();
        }
        if (projectDao.insert(entity) == 1) {
            return projectVo;
        }
        return null;
    }

    /**
     * 添加新项目
     *
     * @param projectVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectVo addProject(ProjectVo projectVo) {
        ProjectTable entity = BeanBeanHelpUtils.copyProperties(projectVo, ProjectTable.class);
        entity.setProjectZoneId(projectZoneSelector.getProjectZoneId());
        // 新建项目文件夹
        File file = new File(projectZoneSelector.getProjectZonePathById(entity.getProjectZoneId()) + "/" + entity.getProjectName());
        if (file.exists()) {
            log.warn("文件路径已经存在，请检查存储空间");
            // TODO 抛出文件路径已经存在异常
            throw CustomExceptionEnum.SERVER_ERROR.getException();
        } else {
            file.mkdir();
        }

        // 保存到数据库
        if (projectDao.insert(entity) == 1)
            return projectVo;
        return null;
    }

    /**
     * 判断该项目下是否含有该类型的计划实施信息
     *
     * @param projectId
     * @param planType
     * @return
     */
    @Override
    public boolean hasProjectPlan(Integer projectId, Boolean planType) {
        ProjectTable projectTable = projectDao.selectById(projectId);
        Integer projectDetailsId = projectTable.getProjectDetailsId();
        ProjectDetailsTable projectDetailsTable = projectDetailsDao.selectById(projectId);
        if (planType) {
            return projectDetailsTable.getProjectPlanId() == null;
        }
        return projectDetailsTable.getProjectPlanPracticalId() == null;
    }

    /**
     * 根据项目id查询项目信息
     *
     * @param id
     * @return
     */
    @Override
    public Optional<ProjectVo> getProjectById(Integer id) {
        ProjectTable projectTable = projectDao.selectById(id);
        ProjectVo projectVo = BeanBeanHelpUtils.copyProperties(projectTable, ProjectVo.class);
        return Optional.ofNullable(projectVo);
    }

    /**
     * 根据项目id以及用户id查询项目信息
     *
     * @param id
     * @param userDetailsId
     * @return
     */
    @Override
    public Optional<ProjectVo> getProjectById(Integer id, Integer userDetailsId) {
        ProjectTable projectTable = projectDao.selectById(id);
        if (userDetailsId.equals(projectTable.getUserId())) {
            ProjectVo projectVo = BeanBeanHelpUtils.copyProperties(projectTable, ProjectVo.class);
            return Optional.ofNullable(projectVo);
        }
        return Optional.empty();
    }
}
