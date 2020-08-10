package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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

        if (!result.isPresent()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_OK.getMessage())
                .data(result.get())
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
}
