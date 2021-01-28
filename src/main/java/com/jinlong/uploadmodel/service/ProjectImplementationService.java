package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.data.ProjectImplementation;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanTableVo;

import java.text.ParseException;
import java.util.List;

public interface ProjectImplementationService {

    /**
     * 新增实施信息
     * @param projectImplementation
     * @return
     */
    Boolean addProjectImplementation(ProjectImplementation projectImplementation);

    /**
     * 修改实施信息
     * @param projectImplementation
     * @return
     */
    Boolean updateProjectImplementation(ProjectImplementation projectImplementation);

    /**
     * 批量删除实施信息
     * @param list
     * @return
     */
    Boolean delProjectImplementationAll(List list);

    /**
     * 模糊查询实施信息
     * @param current
     * @param size
     * @param projectName
     * @param implementationDate
     * @return
     */
    PageVo<ProjectImplementation> getProjectImplementationByLimt(Integer current,Integer size,String projectName,String implementationDate) throws ParseException;
}
