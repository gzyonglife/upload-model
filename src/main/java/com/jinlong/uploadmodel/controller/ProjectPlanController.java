package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.ProjectPlanVo;
import com.jinlong.uploadmodel.service.ProjectPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @description: 项目计划实施
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/3 16:15
 */
@RestController
@RequestMapping("projectPlan")
public class ProjectPlanController {

    @Autowired
    ProjectPlanService projectPlanService;

    // TODO 获取计划列表
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getPlan/projectId")
    public ResponseEntity<ProjectPlanVo> getPlanForProjectId(
            @RequestParam @Validated @NotNull(message = "projectId是必须的") Integer projectId,
            @AuthenticationPrincipal UserDetails user) {
        ProjectPlanVo result = projectPlanService.getPlanForProjectId(projectId, user);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

}
