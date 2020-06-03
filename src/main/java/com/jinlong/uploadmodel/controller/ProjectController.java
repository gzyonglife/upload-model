package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 获取项目列表
     *
     * @param pageVo
     * @param user
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("/getProject/all")
    public ResponseEntity<PageVo<ProjectVo>> getProjectListOfPage(@RequestBody @Validated PageVo pageVo, @AuthenticationPrincipal UserDetails user) {
        PageVo<ProjectVo> result = projectService.getProjectListOfPage(pageVo, user);
        if (result.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 新建项目
     *
     * @param projectVo
     * @param user
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("add")
    public ResponseEntity<Boolean> addProject(@RequestBody @Validated ProjectVo projectVo, @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(projectService.addProject(projectVo, user));
    }
}
