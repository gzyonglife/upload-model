package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectPublicity;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPublicityVo;

public interface ProjectPublicityService {
    /**
     * 添加项目公示信息
     * @param projectPublicity
     * @return
     */
    Boolean addProjectPublicity(ProjectPublicity projectPublicity);

    /**
     * 修改项目公示信息
     * @param projectPublicity
     * @return
     */
    Boolean updateProjectPublicity(ProjectPublicity projectPublicity);

    /**
     * 模糊查询项目公示信息
     * @param projectName
     * @param current
     * @param size
     * @return
     */
    PageVo<ProjectPublicityVo> getProjectPublicity(String projectName, Integer current, Integer size);

    /**
     * 根据项目id查询项目公示信息
     * @param projectId
     * @return
     */
    ProjectPublicity getProjectPublicityById(Integer projectId);
}
