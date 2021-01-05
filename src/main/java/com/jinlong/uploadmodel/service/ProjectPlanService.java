package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanTableVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;

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
     * 获取所有的项目计划
     *
     * @return
     */
    PageVo<ProjectPlanTableVo> getPlanForProject(PageVo pageVo);


}
