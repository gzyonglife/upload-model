package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectCategoryTable;
import com.jinlong.uploadmodel.entity.vo.ProjectCategoryVo;

import java.util.List;

/**
 * @description: ProjectCategoryService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/9 15:32
 */
public interface ProjectCategoryService {
    /**
     * 获取项目分类列表
     *
     * @return
     */
    List<ProjectCategoryVo> getProjectCategoryList();

    /**
     * 创建项目分类
     *
     * @param projectCategoryVo
     * @return
     */
    ProjectCategoryVo createProjectCategory(ProjectCategoryVo projectCategoryVo);

    /**
     * 根据项目分类id获取项目分类信息
     *
     * @param id
     * @return
     */
    ProjectCategoryVo getProjectCategoryById(Integer id);

    /**
     * 根据父分类id获取子分类列表
     *
     * @param id 父分类id
     * @return
     */
    List<ProjectCategoryVo> getProjectCategoryByParentCategoryId(Integer id);

    /**
     * 根据名称查找分类 没有则创建
     * @param projectCategoryName
     * @return
     */
    int getProjectCategoryByadd(String projectCategoryName);

    /**
     * 批量删除分类
     * @param list
     * @return
     */
    Boolean delProjectCategoryAll(List list);

    Boolean updateProjectCategory(ProjectCategoryTable projectCate);



}
