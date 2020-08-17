package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectPlanService;
import com.jinlong.uploadmodel.service.ProjectService;
import com.jinlong.uploadmodel.service.UserService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;


/**
 * @description: 项目计划实施
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 16:15
 */
@Slf4j
@RestController
@RequestMapping("projectPlan")
public class ProjectPlanController {

    @Autowired
    ProjectPlanService projectPlanService;

    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    /**
     * 获取项目计划实施信息
     *
     * @param projectPlanId 计划实施id
     * @param userDetails   当前登录的用户
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getPlan/projectId")
    public ResponseEntity<?> getPlanToProjectId(
            @RequestParam @Validated @NotNull(message = "projectPlanId是必须的") Integer projectPlanId,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断该用户是否有该项目计划实施所属项目的权限
        if (!userService.hasProjectPlan(userDetails, projectPlanId)) {
            log.info("{}用户未拥有{}项目计划实施的权限，禁止获取计划实施信息", userDetails.getUsername(), projectPlanId);
            return ResponseEntity.createFromEnum(CustomResponseEnum.AUTHORITY_INSUFFICIENT);
        }

        // 获取项目计划实施信息
        ProjectPlanVo  result = projectPlanService.getPlanForProjectId(projectPlanId);
        if (result == null) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_PLAN_FAILURE);
        } else {
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.GET_PROJECT_PLAN_OK.getCode())
                    .message(CustomResponseEnum.GET_PROJECT_PLAN_OK.getMessage())
                    .data(result)
                    .build();
        }
    }

    /**
     * 新增项目计划实施信息
     *
     * @param projectPlanVo
     * @param userDetails
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("add")
    public ResponseEntity<?> createPlan(
            @RequestBody @Validated ProjectPlanVo projectPlanVo,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断该用户是否有该项目计划实施所属项目的权限
        if (!userService.hasProject(userDetails, projectPlanVo.getProjectId())) {
            log.info("{}用户未拥有{}项目的权限，禁止为该项目新建计划实施信息", userDetails.getUsername(), projectPlanVo.getProjectId());
            return ResponseEntity.createFromEnum(CustomResponseEnum.AUTHORITY_INSUFFICIENT);
        }
        // 判断该项目计划实施所属项目是否以及含有本信息;
        if (projectService.hasProjectPlan(projectPlanVo.getProjectId(), projectPlanVo.getPlanType())) {
            log.info("{}项目已拥有计划实施信息，禁止为该项目新建计划实施信息", projectPlanVo.getProjectId());
            return ResponseEntity.createFromEnum(CustomResponseEnum.PROJECT_PLAN_EXIST);
        }
        // 进行添加
        Integer planId = projectPlanService.createPlan(projectPlanVo);
        if (planId <= 0) {
            log.info("{}项目添加计划实施信息失败", projectPlanVo.getProjectId());
            return ResponseEntity.createFromEnum(CustomResponseEnum.CREATE_PROJECT_PLAN_FAILURE);
        }
        log.info("{}项目添加计划实施信息成功，新增的计划实施信息id为：{}", projectPlanVo.getProjectId(), planId);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_PLAN_OK.getMessage())
                .data(planId)
                .build();
    }
}
