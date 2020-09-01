package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.ProjectDetailsTable;
import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.entity.vo.ProjectsVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.*;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @description: ProjectController
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 11:48
 */
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectCategoryService projectCategoryService;

    @Autowired
    ProjectPlanService projectPlanService;

    @Autowired
    ProjectDetailsTableService projectDetailsTableService;

    @Autowired
    FirmTableService firmTableService;


    /**
     * 获取项目列表
     *
     * @param current
     * @param size
     * @param userDetails
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/getProject/all")
    public ResponseEntity<?> getProjectListOfPage(
            @RequestParam(name = "current") @Validated @NotNull(message = "current不为空") Long current,
            @RequestParam(name = "size") @Validated @NotNull(message = "current不为空") Long size,
            @AuthenticationPrincipal UserDetails userDetails) {

        PageVo<Object> pageVo = new PageVo<>();
        pageVo.setCurrent(current);
        pageVo.setSize(size);
        PageVo<ProjectVo> result;
        // 判断是否为超级管理员
        if (userDetails.hasRole("SUPERADMIN")) {
            // 查询所有项目
            result = projectService.getProjectListOfPage(pageVo);
        } else {
            // 查询属于自己的项目
            result = projectService.getProjectListOfPage(pageVo, userDetails.getId());
        }

        if (result == null || result.getData().isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_LIST_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_LIST_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 新建项目
     *
     * @param projectVo
     * @param userDetails
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("add")
    public ResponseEntity<?> addProject(@RequestBody @Validated ProjectVo projectVo, @AuthenticationPrincipal UserDetails userDetails) {
        ProjectVo result;
        // 判断是否为超级管理员
        if (userDetails.hasRole("SUPERADMIN")) {
            // 判断是否是给自己添加
            if (projectVo.getUserId() == null) {
                projectVo.setUserId(userDetails.getId());
            }
            // 直接添加所有信息
            result = projectService.addProject(projectVo);
        } else {
            result = projectService.addProject(projectVo, userDetails.getId());
        }
        if (result == null) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.CREATE_PROJECT_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 根据项目id获取项目信息
     *
     * @param id
     * @param userDetails
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/getProject/id")
    public ResponseEntity<?> getProjectById(@RequestParam @Validated @NotNull(message = "项目id不得为空") Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<ProjectVo> result;
        // 判断是否为超级管理员
        if (userDetails.hasRole("SUPERADMIN")) {
            // 允许查询
            result = projectService.getProjectById(id);
        } else {
            // 查询属于自己的项目
            result = projectService.getProjectById(id, userDetails.getId());
        }
        ProjectsVo projectsVo = new ProjectsVo();
        ProjectVo projectVo = result.get();
        if(id==null||id==0){
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_BY_ID_Null);
        }
        projectsVo.setProjectName(projectVo.getProjectName());
        projectsVo.setProjectId(projectVo.getProjectId());
        projectsVo.setLongitudeAndLatitude(projectVo.getLongitudeAndLatitude());
        projectsVo.setCateGoryName(projectCategoryService.getProjectCategoryById(projectVo.getProjectCategoryId()).getProjectCategoryName());
        projectsVo.setFocus(projectVo.getIsFocus()==1?true:false);
        ProjectDetailsTable projectDetailsTable = projectDetailsTableService.getProjectDetailsTableById(projectVo.getProjectId());
        List<ProjectPlanTable> projectPlanTableList = projectPlanService.getPlanForProjectIds(projectVo.getProjectId());
        projectsVo.setBuild(firmTableService.getFirmTableById(projectDetailsTable.getConstructionFirmId()));
        projectsVo.setAgentConstruction(firmTableService.getFirmTableById(projectDetailsTable.getAgentConstructionFirmId()));
        projectsVo.setCoordination(firmTableService.getFirmTableById(projectDetailsTable.getCooperateFirmId()));
        for(ProjectPlanTable planTable:projectPlanTableList){
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            String plan = "";
            switch (month){
                case 1:
                    plan = planTable.getProjectPlanJanuary();
                    break;
                case 2:
                    plan = planTable.getProjectPlanFebruary();
                    break;
                case 3:
                    plan = planTable.getProjectPlanMarch();
                    break;
                case 4:
                    plan = planTable.getProjectPlanApril();
                    break;
                case 5:
                    plan = planTable.getProjectPlanMay();
                    break;
                case 6:
                    plan = planTable.getProjectPlanJune();
                    break;
                case 7:
                    plan = planTable.getProjectPlanJuly();
                    break;
                case 8:
                    plan = planTable.getProjectPlanAugust();
                    break;
                case 9:
                    plan = planTable.getProjectPlanSeptember();
                    break;
                case 10:
                    plan = planTable.getProjectPlanOctober();
                    break;
                case 11:
                    plan = planTable.getProjectPlanNovember();
                    break;
                case 12:
                    plan = planTable.getProjectPlanDecember();
                    break;
            }
            if(planTable.getPlanType()){//计划
                projectsVo.setProjectTimeOpen(planTable.getProjectPlanExpectStartTime());
                projectsVo.setProjectTimeDown(planTable.getProjectPlanExpectEndTime());
                projectsVo.setTarget(plan);
            }else{//实施
                projectsVo.setProjectTimeOpens(planTable.getProjectPlanExpectStartTime());
                projectsVo.setProjectTimeDowns(planTable.getProjectPlanExpectEndTime());
                projectsVo.setActual(plan);
            }
            projectsVo.setKilometers(planTable.getProjectPlanInvestTotal());
            projectsVo.setKilometersYear(planTable.getProjectPlanInvestFinish());

        }
        if (!result.isPresent()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_BY_ID_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_BY_ID_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_BY_ID_OK.getMessage())
                .data(projectsVo)
                .build();
    }

    /**
     * 根据分类id获取项目
     *
     * @param categoryId
     * @param userDetails
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/getProject/categoryId")
    public ResponseEntity<?> getProjectByCategoryId(@RequestParam @Validated @NotNull(message = "分类id不得为空") Integer categoryId, @AuthenticationPrincipal UserDetails userDetails) {
        List<ProjectVo> result;
        // 判断是否为超级管理员
        if (userDetails.hasRole("SUPERADMIN")) {
            // 允许查询
            result = projectService.getProjectByCategoryId(categoryId);
        } else {
            // 查询属于自己的项目
            result = projectService.getProjectByCategoryId(categoryId, userDetails.getId());
        }
        if (result.isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_OK.getMessage())
                .data(result)
                .build();
    }


    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/search/project")
    public ResponseEntity<?> searchProjectForPage(@RequestParam(name = "projectCategoryId", required = false) Integer projectCategoryId,
                                                  @RequestParam(name = "name", required = false) String name,
                                                  @RequestParam(name = "year", required = false) String year,
                                                  @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                                                  @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        PageVo<ProjectVo> projectVoPageVo = projectService.searchProjectForPage(projectCategoryId,name,year,current,size);
        Assert.assertNotNull(projectVoPageVo, CustomExceptionEnum.GET_NONE);
        Assert.assertCollectionNotEmpty(projectVoPageVo.getData(), CustomExceptionEnum.GET_NONE);
        return ResponseEntity
                .builder()
                .data(projectVoPageVo)
                .code(CustomResponseEnum.GET_PROJECT_PAGE_SEARCH_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_PAGE_SEARCH_OK.getMessage())
                .build();
    }
}
