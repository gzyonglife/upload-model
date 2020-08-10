package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.ModelShowVo;
import com.jinlong.uploadmodel.entity.vo.ProjectModelVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectModelService;
import com.jinlong.uploadmodel.service.ProjectModelTypeService;
import com.jinlong.uploadmodel.service.UserService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @description: 项目模型
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/19 16:07
 */
@RestController
@RequestMapping("projectMode")
public class ProjectModelController {

    @Autowired
    ProjectModelService projectModelService;

    @Autowired
    ProjectModelTypeService projectModelTypeService;

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @ResponseBody
    @PostMapping("/uploadFolder")
    public ResponseEntity<?> uploadFolder(
            @RequestParam("folder") MultipartFile[] folder,
            @RequestParam("projectModelName") String projectModelName,
            @RequestParam("projectId") Integer projectId,
            @RequestParam("projectModelTime") @DateTimeFormat(pattern = "yyyy-HH-dd") Date projectModelTime,
            @RequestParam("projectModelTypeId") Integer projectModelTypeId,
            @RequestParam(name = "projectModelView", required = false) String projectModelView,
            @AuthenticationPrincipal UserDetails userDetails) {
        Set<String> set = new HashSet<>();
        ProjectModelVo projectModelVo = new ProjectModelVo();
        projectModelVo.setCreateTime(new Date());
        projectModelVo.setProjectId(projectId);
        projectModelVo.setProjectModelTypeId(projectModelTypeId);
        projectModelVo.setProjectModelView(projectModelView);

        projectModelVo.setProjectModelName(projectModelName);
        projectModelVo.setProjectModelTime(projectModelTime);
        projectModelVo.setCreateUserId(userDetails.getId());

        // 判断是否为文件夹
        if (projectModelTypeService.isFolder(projectModelTypeId))
            // 获取文件夹
            projectModelVo.setProjectModelPath(projectModelName + "/" + folder[0].getOriginalFilename().substring(0, folder[0].getOriginalFilename().indexOf("/")));
        else
            projectModelVo.setProjectModelPath(projectModelName + "/" + folder[0].getOriginalFilename().substring(folder[0].getOriginalFilename().lastIndexOf("/")+1));

        Optional<ProjectModelVo> result = projectModelService.saveProjectModel(projectModelVo, folder);

        if (!result.isPresent()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPLOAD_PROJECT_MODEL_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.UPLOAD_PROJECT_MODEL_OK.getCode())
                .message(CustomResponseEnum.UPLOAD_PROJECT_MODEL_OK.getMessage())
                .data(result.get())
                .build();
    }

    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @ResponseBody
    @GetMapping("/getProjectModel/projectId")
    ResponseEntity<?> getProjectModelListByProjectId(
            @RequestParam @Validated @NotNull(message = "项目id不得为空") Integer projectId,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断是否有该项目的权限
        if (userService.hasProject(userDetails, projectId)) {
            List<ModelShowVo> result = projectModelService.getProjectModelListByProjectId(projectId);
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.GET_PROJECT_MODEL_OK.getCode())
                    .message(CustomResponseEnum.GET_PROJECT_MODEL_OK.getMessage())
                    .data(result)
                    .build();
        }
        return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_MODEL_FAILURE);
    }

    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @ResponseBody
    @GetMapping("/getProjectModel/all")
    ResponseEntity<?> getProjectModelList(
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断是否有该项目的权限
        List<ModelShowVo> result = projectModelService.getProjectModelList();

        if (result.isEmpty()){
            return ResponseEntity
                    .createFromEnum(CustomResponseEnum.GET_PROJECT_MODEL_NOT_EXIST);
        }

        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_MODEL_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_MODEL_OK.getMessage())
                .data(result)
                .build();
    }

    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @ResponseBody
    @PutMapping("/modifyProjectModel/view")
    ResponseEntity<?> modifyProjectModelForView(
            @RequestParam("projectModelId") @Validated @NotEmpty(message = "模型id不得为空") Integer projectModelId,
            @RequestParam("projectModelView") @Validated @NotEmpty(message = "模型视角不得为空") String projectModelView,
            @AuthenticationPrincipal UserDetails userDetails) {
        Optional<ProjectModelVo> result = projectModelService.modifyProjectModelForView(projectModelId, projectModelView);
        if (!result.isPresent()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_MODEL_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_OK.getMessage())
                .data(result.get())
                .build();
    }
}
