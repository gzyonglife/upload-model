package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.ProjectDetailsTableDao;
import com.jinlong.uploadmodel.dao.ProjectPlanTableDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.ProjectDetailsTable;
import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;
import com.jinlong.uploadmodel.service.ProjectPlanService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: ProjectPlanServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/4 9:05
 */
@Slf4j
@Service
public class ProjectPlanServiceImpl implements ProjectPlanService {

    @Autowired
    ProjectPlanTableDao projectPlanDao;

    @Autowired
    ProjectTableDao projectDao;

    @Autowired
    ProjectDetailsTableDao projectDetailsDao;

    /**
     * 获取项目计划实施信息
     *
     * @param projectPlanId
     * @return
     */
    @Override
    public ProjectPlanVo getPlanForProjectId(Integer projectPlanId) {
        ProjectPlanTable planTable = projectPlanDao.selectById(projectPlanId);
        return BeanBeanHelpUtils.copyProperties(planTable, ProjectPlanVo.class);
    }
    /**
     * 获取项目计划实施信息
     *
     * @param projectId
     * @return
     */
    @Override
    public List<ProjectPlanTable> getPlanForProjectIds(Integer projectId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("project_id",projectId);
        List<ProjectPlanTable> list = projectPlanDao.selectByMap(map);
        return list;
    }
    /**
     * 添加项目计划实施信息
     *
     * @param projectPlanVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createPlan(ProjectPlanVo projectPlanVo) {

        // 对象转化
        ProjectPlanTable projectPlanTable = BeanBeanHelpUtils.copyProperties(projectPlanVo, ProjectPlanTable.class);
        Assert.assertNotNull(projectPlanTable, CustomExceptionEnum.CREATE_PLAN_ERROR);
        Integer projectId = projectPlanVo.getProjectId();

        // 新增项目计划实施表信息
        projectPlanDao.insert(projectPlanTable);
        Integer projectPlanId = projectPlanTable.getProjectPlanId();

        // 获取项目详情表id
        ProjectTable projectTable = projectDao.selectById(projectId);
        Integer projectDetailsId = projectTable.getProjectDetailsId();

        // 填充属性
        ProjectDetailsTable projectDetailsTable = new ProjectDetailsTable();

        if (projectPlanVo.getPlanType()) {
            projectDetailsTable.setProjectPlanId(projectPlanId);
        } else {
            projectDetailsTable.setProjectPlanPracticalId(projectPlanId);
        }


        // 修改该项目的详细信息表
        projectDetailsDao.update(projectDetailsTable, new QueryWrapper<ProjectDetailsTable>().eq("project_details_id", projectDetailsId));


        return projectPlanId;
    }
}
