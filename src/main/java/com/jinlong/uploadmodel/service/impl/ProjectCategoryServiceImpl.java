package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.ProjectCategoryTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectCategoryTable;
import com.jinlong.uploadmodel.entity.vo.ProjectCategoryVo;
import com.jinlong.uploadmodel.service.ProjectCategoryService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @description: ProjectCategoryServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/9 15:32
 */
@Slf4j
@Service
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    @Autowired
    ProjectCategoryTableDao projectCategoryDao;

    /**
     * 获取项目分类列表
     *
     * @return
     */
    @Override
    public List<ProjectCategoryVo> getProjectCategoryList() {
        List<ProjectCategoryTable> projectCategoryTableList = projectCategoryDao.selectList(new QueryWrapper<>());
        return BeanBeanHelpUtils.copyList(projectCategoryTableList, ProjectCategoryVo.class);
    }

    /**
     * 创建项目分类
     *
     * @param projectCategoryVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectCategoryVo createProjectCategory(ProjectCategoryVo projectCategoryVo) {
        ProjectCategoryTable table = BeanBeanHelpUtils.copyProperties(projectCategoryVo, ProjectCategoryTable.class);
        projectCategoryDao.insert(table);
        projectCategoryVo.setProjectCategoryId(table.getProjectCategoryId());
        return projectCategoryVo;
    }

    /**
     * 根据项目分类id获取项目分类信息
     *
     * @param id
     * @return
     */
    @Override
    public ProjectCategoryVo getProjectCategoryById(Integer id) {
        ProjectCategoryTable table = projectCategoryDao.selectById(id);
        ProjectCategoryVo projectCategoryVo = BeanBeanHelpUtils.copyProperties(table, ProjectCategoryVo.class);
        Assert.assertNotNull(projectCategoryVo, CustomExceptionEnum.MODIFY_USER_ERROR);
        return projectCategoryVo;
    }

    /**
     * 根据父分类id获取子分类列表
     *
     * @param id 父分类id
     * @return
     */
    @Override
    public List<ProjectCategoryVo> getProjectCategoryByParentCategoryId(Integer id) {
        List<ProjectCategoryTable> categoryTables = projectCategoryDao.selectList(new QueryWrapper<ProjectCategoryTable>().eq("project_parent_category_id", id));
        if (categoryTables.isEmpty()) {
            return Collections.emptyList();
        }
        List<ProjectCategoryVo> categoryVos = BeanBeanHelpUtils.copyList(categoryTables, ProjectCategoryVo.class);
        Assert.assertCollectionNotEmpty(categoryVos,CustomExceptionEnum.SERVER_ERROR);
        return categoryVos;
    }
}
