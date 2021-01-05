package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.data.*;
import com.jinlong.uploadmodel.entity.vo.*;
import com.jinlong.uploadmodel.service.*;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    StatisticsProjectTypeService statisticsProjectTypeService;

    @Autowired
    ProjectCategoryService projectCategoryService;

    @Autowired
    OverviewAdministrativePlanProjectService overviewAdministrativePlanProjectService;

    @Autowired
    AdministrativeTableBindingAdministrativeTableService administrativeTableBindingAdministrativeTableService;

    @Autowired
    AdministrativeTableService administrativeTableService;

    @Autowired
    AdministrativePlanService administrativePlanService;

    @Autowired
    OverviewProjectService overviewProjectService;

    @Autowired
    ProjectClassTableService projectClassTableService;

    @Autowired
    OverviewYearPlanService overviewYearPlanService;

    @Autowired
    ProjectDetailsTableService projectDetailsTableService;

    @Autowired
    ProjectService projectService;
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getProjectCategory/all")
    public ResponseEntity<?> getStatisticsProjectType() {
        IndexVo indexVo = new IndexVo();
        List<StatisticsProjectType> statisticsList = statisticsProjectTypeService.getStatisticsProjectType();
        List<OverviewAdministrativePlanProject> overviewList = overviewAdministrativePlanProjectService.getOverviewAdministrativePlanProject();
        List<OverviewYearPlan> overviewYearPlanList = overviewYearPlanService.getOverviewYearPlan();
        List<OverviewAdministrativePlanProjectVo> overviewAdministrativePlanProjectVoList = new ArrayList<OverviewAdministrativePlanProjectVo>();
        List<StatisticsProjectTypeVo> statisticsProjectTypeVoList = new ArrayList<StatisticsProjectTypeVo>();
        List<OverviewYearPlanVo> overviewYearPlanVoList = new ArrayList<OverviewYearPlanVo>();
        StatisticsProjectTypeVo statisticsProjectTypeVo = new StatisticsProjectTypeVo();
        OverviewAdministrativePlanProjectVo overviewAdministrativePlanProjectVo = new OverviewAdministrativePlanProjectVo();
        OverviewYearPlanVo overviewYearPlanVo = new OverviewYearPlanVo();
        for(StatisticsProjectType lists:statisticsList) {
            ProjectCategoryVo projectCategoryVo = projectCategoryService.getProjectCategoryById(lists.getProjectCategoryId());
            statisticsProjectTypeVo = BeanBeanHelpUtils.copyProperties(lists, statisticsProjectTypeVo.getClass());
            statisticsProjectTypeVo.setProject_category_name(projectCategoryVo.getProjectCategoryName());
            statisticsProjectTypeVoList.add(statisticsProjectTypeVo);
        }
        for(OverviewAdministrativePlanProject list:overviewList){
            AdministrativeTableBindingAdministrativeTable administrativeTableBindingAdministrativeTable =
                    administrativeTableBindingAdministrativeTableService.
                            getAdministrativeTableBindingAdministrativeTable(list.getAdministrativeTableBindingAdministrativeTableId());
            overviewAdministrativePlanProjectVo = BeanBeanHelpUtils.copyProperties(list, OverviewAdministrativePlanProjectVo.class);
            overviewAdministrativePlanProjectVo.setAdministrativeTableBindingAdministrativeTableName(administrativeTableService.
                    getAdministrativeTableId(administrativeTableBindingAdministrativeTable.getAdministrativeTableId()).
                    getAdministrativeName());
            overviewAdministrativePlanProjectVo.setAdministrativeName(administrativePlanService.getAdministrativePlanById(administrativeTableBindingAdministrativeTable.getAdministrativePlanId()).getAdministrativePlanName());
            overviewAdministrativePlanProjectVoList.add(overviewAdministrativePlanProjectVo);
        }
        for(OverviewYearPlan list:overviewYearPlanList){
            ProjectClassTable projectClassTable = projectClassTableService.getProjectClassTableById(list.getProjectClassTableId());
            overviewYearPlanVo = BeanBeanHelpUtils.copyProperties(list, OverviewYearPlanVo.class);
            overviewYearPlanVo.setProjectClassTableName(projectClassTable.getProjectClassTableName());
            overviewYearPlanVoList.add(overviewYearPlanVo);
        }
        List<ProjectTable> projectTableList = projectService.getProjectByFoucus();
        List<ProjectVo> projectVoList = new ArrayList<ProjectVo>();
        for(ProjectTable list:projectTableList){
            ProjectVo projectVo = BeanBeanHelpUtils.copyProperties(list, ProjectVo.class);
            projectVo.setProjectClassTableName(projectCategoryService.getProjectCategoryById(list.getProjectCategoryId()).getProjectCategoryName());
            projectVoList.add(projectVo);
        }
        indexVo.addData(statisticsProjectTypeVoList);
        indexVo.addData(overviewAdministrativePlanProjectVoList);
        indexVo.addData(overviewProjectService.getOverviewProject());
        indexVo.addData(overviewYearPlanVoList);
        indexVo.addData(projectVoList);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_STATISTICS_PROJECT_TYPE_OK.getCode())
                .message(CustomResponseEnum.GET_STATISTICS_PROJECT_TYPE_OK.getMessage())
                .data(indexVo)
                .build();
    }
}
