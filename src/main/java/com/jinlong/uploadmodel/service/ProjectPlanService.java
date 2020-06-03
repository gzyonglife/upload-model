package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;

/**
 * @description: ProjectPlanService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 20:01
 */
public interface ProjectPlanService {
    // TODO
    ProjectPlanVo getPlanForProjectId(Integer projectId, UserDetails user);
}
