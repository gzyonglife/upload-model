package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanTableVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @description: ProjectPlanService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 20:01
 */
public interface ProjectPlanService {
    /**
     * 获取项目计划实施信息
     *
     * @param projectPlanId
     * @return
     */
    ProjectPlanVo getPlanForProjectId(Integer projectPlanId);

    /**
     * 根据项目id获取计划
     *
     * @param projectId
     * @return
     */
    List<ProjectPlanTable> getPlanForProjectIds(Integer projectId);

    /**
     * 添加项目计划实施信息
     *
     * @param projectPlanVo
     * @return
     */
    Integer createPlan(ProjectPlanVo projectPlanVo);

    /**
     * 添加项目计划实施信息
     *
     * @param projectPlanTable
     * @return
     */
    Integer insertProjectPlan(ProjectPlanTable projectPlanTable);

    /**
     * 修改项目计划实施信息
     *
     * @param projectPlanVo
     * @return
     */
    Integer upodateProjectPlan(ProjectPlanVo projectPlanVo);

    /**
     * 获取所有的项目计划
     *
     * @return
     */
    PageVo<ProjectPlanTableVo> getPlanForProject(PageVo pageVo);

    /**
     *
     * @param projectPlanId
     * @return
     */
    Boolean delProjectPlan(List projectPlanId);

    /**
     * 模糊查询
     * @param current
     * @param size
     * @param projectName
     * @param projectPlanYear
     * @param planType
     * @return
     */
    PageVo<ProjectPlanTableVo> getProjectPlanByLike(Integer current,Integer size,String projectName,
                                                  String projectPlanYear,Integer planType) throws ParseException;

    /**
     * 批量删除
     * @param typeId
     * @return
     */
    Boolean delProjectPlanByList(List typeId);
}
