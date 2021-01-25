package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
     * @return
     */
    PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo,Integer type);

    /**
     * 获取项目列表
     *
     * @param pageVo
     * @param userId
     * @return
     */
    PageVo<ProjectVo> getProjectListOfPage(PageVo pageVo, Integer userId,Integer type);

    /**
     * 根据项目分类id查询项目
     *
     * @param categoryId
     * @param userId
     * @return
     */
    List<ProjectVo> getProjectByCategoryId(Integer categoryId, Integer userId);

    /**
     * 根据项目分类id查询项目
     *
     * @param categoryId
     * @return
     */
    List<ProjectVo> getProjectByCategoryId(Integer categoryId);

    /**
     * 添加新项目
     *
     * @param projectVo
     * @param userId
     * @return
     */
    ProjectVo addProject(ProjectVo projectVo, Integer userId);

    /**
     * 添加新项目
     *
     * @param projectVo
     * @return
     */
    ProjectVo addProject(ProjectVo projectVo);

    /**
     * 判断该项目下是否含有该类型的计划实施信息
     *
     * @param projectId
     * @param planType
     * @return
     */
    boolean hasProjectPlan(Integer projectId, Boolean planType);

    /**
     * 根据项目id查询项目信息
     *
     * @param id
     * @return
     */
    Optional<ProjectVo> getProjectById(Integer id);

    /**
     * 根据项目id以及用户id查询项目信息
     *
     * @param id
     * @param userDetailsId
     * @return
     */
    Optional<ProjectVo> getProjectById(Integer id, Integer userDetailsId);

    /**
     * 重点项目查询
     *
     * @return
     */
    List<ProjectTable> getProjectByFoucus();


    /**
     * 搜索并分页获取项目列表
     * @param projectCategoryId
     * @param name
     * @param year
     * @param current
     * @param size
     * @return
     */
    PageVo<ProjectVo> searchProjectForPage(Integer projectCategoryId, String name, String year, Integer current, Integer size);

    /**
     * 批量删除项目
     * @param list
     * @return
     */
    Boolean delPeojectAll(List list);

    /**
     * 修改项目信息
     * @param projectVo
     * @return
     */
    ProjectVo updateProject(ProjectVo projectVo);

    Boolean addImgVideo(MultipartFile[] imgFolder,
                        MultipartFile[] videoFolder,
                        Integer projectId);
}
