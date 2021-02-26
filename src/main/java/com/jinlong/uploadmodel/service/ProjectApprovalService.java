package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectApproval;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectApprovalVo;

public interface ProjectApprovalService {
    /**
     * 添加项目批准情况
     * @param ProjectApproval
     * @return
     */
    Boolean addProjectApproval(ProjectApproval ProjectApproval);

    /**
     * 模糊查询分页
     * @param projectName
     * @param current
     * @param size
     * @return
     */
    PageVo<ProjectApprovalVo> getProjectApproval(String projectName, Integer current, Integer size);

    /**
     * 修改项目批准情况
     * @param ProjectApproval
     * @return
     */
    Boolean updateProjectApproval(ProjectApproval ProjectApproval);

    /**
     * 根据项目id查询项目批准情况
     * @param projectId
     * @return
     */
    ProjectApproval getProjectApprovalById(Integer projectId);
}
