package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectBeCompleted;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectBeCompletedVo;

public interface ProjectBeCompletedService {
    /**
     * 项目竣工信息添加
     * @param ProjectBeCompleted
     * @return
     */
    Boolean addProjectBeCompleted(ProjectBeCompleted ProjectBeCompleted);

    /**
     * 修改项目竣工信息
     * @param ProjectBeCompleted
     * @return
     */
    Boolean updateProjectBeCompleted(ProjectBeCompleted ProjectBeCompleted);

    /**
     * 模糊查询项目竣工信息
     * @param projectName
     * @param current
     * @param size
     * @return
     */
    PageVo<ProjectBeCompletedVo> getProjectBeCompleted(String projectName, Integer current, Integer size);
}
