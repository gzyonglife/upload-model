package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;

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
     * 添加项目计划实施信息
     *
     * @param projectPlanVo
     * @return
     */
    Integer createPlan(ProjectPlanVo projectPlanVo);
}
