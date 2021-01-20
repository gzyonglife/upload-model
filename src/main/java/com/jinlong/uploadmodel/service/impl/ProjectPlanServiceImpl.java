package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.FirmTableDao;
import com.jinlong.uploadmodel.dao.ProjectDetailsTableDao;
import com.jinlong.uploadmodel.dao.ProjectPlanTableDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.entity.data.*;
import com.jinlong.uploadmodel.entity.vo.*;
import com.jinlong.uploadmodel.service.ProjectPlanService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    FirmTableDao firmTableDao;
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("project_id", projectId);
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

        if(projectPlanVo.getConstructionFirm()!=null){
            FirmTable firmTable = firmTableDao.selectOne(new QueryWrapper<FirmTable>().eq("firm_name",projectPlanVo.getConstructionFirm()));
            if(firmTable!=null){
                projectPlanTable.setConstructionFirmId(firmTable.getFirmId());
            }else{
                firmTable.setFirmName(projectPlanVo.getConstructionFirm());
                firmTableDao.insert(firmTable);
            }
        }
        if(projectPlanVo.getAgentConstructionFirm()!=null){
            FirmTable firmTable = firmTableDao.selectOne(new QueryWrapper<FirmTable>().eq("firm_name",projectPlanVo.getAgentConstructionFirm()));
            if(firmTable!=null){
                projectPlanTable.setAgentConstructionFirmId(firmTable.getFirmId());
            }else{
                firmTable.setFirmName(projectPlanVo.getAgentConstructionFirm());
                firmTableDao.insert(firmTable);
            }
        }
        if(projectPlanVo.getCooperateFirm()!=null){
            FirmTable firmTable = firmTableDao.selectOne(new QueryWrapper<FirmTable>().eq("firm_name",projectPlanVo.getCooperateFirm()));
            if(firmTable!=null){
                projectPlanTable.setCooperateFirmId(firmTable.getFirmId());
            }else{
                firmTable.setFirmName(projectPlanVo.getCooperateFirm());
                firmTableDao.insert(firmTable);
            }
        }
        // 新增项目计划实施表信息
        projectPlanDao.insert(projectPlanTable);


        return projectId;
    }

    @Override
    public Integer insertProjectPlan(ProjectPlanTable projectPlanTable) {
        return projectPlanDao.insert(projectPlanTable);
    }

    @Override
    public Integer upodateProjectPlan(ProjectPlanVo projectPlanVo) {
        // 对象转化
        ProjectPlanTable projectPlanTable = BeanBeanHelpUtils.copyProperties(projectPlanVo, ProjectPlanTable.class);
        return projectPlanDao.updateById(projectPlanTable);
    }

    @Override
    public PageVo<ProjectPlanTableVo> getPlanForProject(PageVo pageVo) {
        Page<ProjectPlanTable> tablePage = projectPlanDao.selectPage(new Page<>(pageVo.getCurrent(), pageVo.getSize())
                , new QueryWrapper<ProjectPlanTable>().orderByDesc("project_plan_expect_start_time"));
        List<ProjectPlanTableVo> planVo = PageVo.createPageVoOfPage(tablePage, ProjectPlanTableVo.class).getData();
        List<ProjectPlanTableVo> planVos = new ArrayList<ProjectPlanTableVo>();
        if(planVo==null||planVo.size()==0){
            return null;
        }
        for(ProjectPlanTableVo list:planVo){
            if(projectDao.selectById(list.getProjectId())!=null){
                list.setProjectName(projectDao.selectById(list.getProjectId()).getProjectName());
            }else{
                list.setProjectName("项目已被删除");
            }
            planVos.add(list);
        }
        PageVo<ProjectPlanTableVo> pagevoplan = PageVo.createPageVoOfPage(tablePage, ProjectPlanTableVo.class);
        pagevoplan.setData(planVos);
        return pagevoplan;
    }

    @Override
    public Boolean delProjectPlan(List projectPlanId) {
        return projectPlanDao.deleteBatchIds(projectPlanId)>=1?true:false;
    }

    @Override
    public PageVo<ProjectPlanTableVo> getProjectPlanByLike(Integer current, Integer size, String projectName, String projectPlanYear, Integer planType) throws ParseException {

        Page<ProjectPlanTable> page = new Page<>(current, size);
        List<ProjectTable> listProject = null;
        List<Integer> projectIds=Collections.emptyList();
        if(projectName!=null && !projectName.equals("")){
            listProject = projectDao.selectList(new QueryWrapper<ProjectTable>().like("project_name",projectName));
            if(listProject==null||listProject.isEmpty()){
                return null;
            }else{
                projectIds=new ArrayList<>(listProject.size());
                for(ProjectTable plt : listProject){
                    projectIds.add(plt.getProjectId());
                }
            }
        }
        List<ProjectPlanTableVo> lis = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Page<ProjectPlanTable> projectPlanTablePage = null;
        if(projectPlanYear!=null&&!projectPlanYear.equals("")&&projectIds!=null){
            projectPlanTablePage = projectPlanDao.selectPage(page, new QueryWrapper<ProjectPlanTable>().lambda()
                    .eq(planType!=null,ProjectPlanTable::getPlanType, planType)
                    .eq(projectPlanYear!=null&&!projectPlanYear.equals(""),ProjectPlanTable::getProjectPlanYear, sdf.parse(projectPlanYear + "-01-01"))
                    .in(projectIds!=null&&!projectIds.isEmpty(),ProjectPlanTable::getProjectId, projectIds));
        }else{
            projectPlanTablePage = projectPlanDao.selectPage(page, new QueryWrapper<ProjectPlanTable>().lambda()
                    .eq(planType!=null,ProjectPlanTable::getPlanType, planType)
                    .in(projectIds!=null&&!projectIds.isEmpty(),ProjectPlanTable::getProjectId, projectIds));
        }

//        if(projectPlanDao.selectList(que).size()!=0){
//            page.getRecords().addAll(projectPlanDao.selectPage(page,que).getRecords());
//        }


        if(projectPlanTablePage==null||projectPlanTablePage.getRecords().size()==0){
            return null;
        }
        PageVo<ProjectPlanTableVo> pageVo = BeanBeanHelpUtils.copyProperties(projectPlanTablePage, PageVo.class);
        List<ProjectPlanTableVo> li = new ArrayList<>();
        for(ProjectPlanTableVo list:BeanBeanHelpUtils.copyList(projectPlanTablePage.getRecords(), ProjectPlanTableVo.class)){
            if(projectDao.selectById(list.getProjectId())!=null){
                list.setProjectName(projectDao.selectById(list.getProjectId()).getProjectName());
            }else{
                list.setProjectName("项目已被删除");
            }
            if(list.getConstructionFirmId()!=null&&list.getConstructionFirmId()!=0){
                FirmTable firmTable = firmTableDao.selectById(list.getConstructionFirmId());
                if(firmTable!=null){
                    list.setConstructionFirm(firmTable);
                }
            }
            if(list.getAgentConstructionFirmId()!=null&&list.getAgentConstructionFirmId()!=0){
                FirmTable firmTable = firmTableDao.selectById(list.getAgentConstructionFirmId());
                if(firmTable!=null){
                    list.setAgentConstructionFirm(firmTable);
                }
            }
            if(list.getCooperateFirmId()!=null&&list.getCooperateFirmId()!=0){
                FirmTable firmTable = firmTableDao.selectById(list.getCooperateFirmId());
                if(firmTable!=null){
                    list.setCooperateFirm(firmTable);
                }
            }
            li.add(list);
        }
        pageVo.setData(li);
        return pageVo;
    }

    @Override
    public Boolean delProjectPlanByList(List typeId) {
        return projectPlanDao.deleteBatchIds(typeId)>=1?true:false;
    }
}
