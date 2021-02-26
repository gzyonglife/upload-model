package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.ModelShowVo;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ProjectModelVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectModelService;
import com.jinlong.uploadmodel.service.ProjectModelTypeService;
import com.jinlong.uploadmodel.service.UserService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
        //projectModelVo.setCreateUserId(userDetails.getId());

        // 判断是否为文件夹
        if (projectModelTypeService.isFolder(projectModelTypeId))
            // 获取文件夹
            projectModelVo.setProjectModelPath(projectModelName + "/" + folder[0].getOriginalFilename().substring(0, folder[0].getOriginalFilename().indexOf("/")));
        else
            projectModelVo.setProjectModelPath(projectModelName + "/" + folder[0].getOriginalFilename().substring(folder[0].getOriginalFilename().lastIndexOf("/") + 1));

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

    @ResponseBody
    @GetMapping("/getProjectModel/projectId")
    ResponseEntity<?> getProjectModelListByProjectId(
            @RequestParam @Validated @NotNull(message = "项目id不得为空") Integer projectId,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断是否有该项目的权限
        //if (userService.hasProject(userDetails, projectId)) {
        List<ModelShowVo> result = projectModelService.getProjectModelListByProjectId(projectId);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_MODEL_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_MODEL_OK.getMessage())
                .data(result)
                .build();
        //}
        //return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_MODEL_FAILURE);
    }

    @ResponseBody
    @GetMapping("/getProjectModel/all")
    ResponseEntity<?> getProjectModelList(
            Integer size,
            Integer current,
            @RequestParam @Validated @NotNull(message = "type不得为空") Integer type,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断是否有该项目的权限
        PageVo<ModelShowVo> result = projectModelService.getProjectModelList(type,current,size);

        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_MODEL_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_MODEL_OK.getMessage())
                .data(result)
                .build();
    }

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

    @ResponseBody
    @PostMapping("/updateModel")
    ResponseEntity<?> updateModel(@RequestParam(name = "projectModelId") Integer projectModelId,
                                  @RequestParam(name = "projectId", required = false) Integer projectId,
                                  @RequestParam(name = "projectModelName", required = false) String projectModelName,
                                  @RequestParam(name = "projectModelTypeId", required = false) Integer projectModelTypeId,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        Boolean modelBool = projectModelService.updateModel(projectModelId, projectId, projectModelName, projectModelTypeId);
        if (modelBool) {
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.UPDATE_PROJECT_MODEL_OK.getCode())
                    .message(CustomResponseEnum.UPDATE_PROJECT_MODEL_OK.getMessage())
                    .data(projectModelService.getModelById(projectModelId))
                    .build();
        }
        return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECT_MODEL_ERROR);
    }

    @PostMapping("/getModelId")
    ResponseEntity<?> getModelById(@RequestParam(name = "projectModelId") Integer projectModelId,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_MODEL_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_MODEL_OK.getMessage())
                .data(projectModelService.getModelById(projectModelId))
                .build();
    }

    @PostMapping("/delModelId")
    ResponseEntity<?> delModelId(@RequestParam(name = "idList") List<Integer> idList,
                                 @AuthenticationPrincipal UserDetails userDetails){
        if(!projectModelService.delModelType(idList)){
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.DEL_PROJECT_MODEL_ERROR.getCode())
                    .message(CustomResponseEnum.DEL_PROJECT_MODEL_ERROR.getMessage())
                    .data(false)
                    .build();
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.DEL_PROJECT_MODEL_OK.getCode())
                .message(CustomResponseEnum.DEL_PROJECT_MODEL_OK.getMessage())
                .data(true)
                .build();
    }

    @GetMapping("/getModelByLimt")
    ResponseEntity<?> getModelByLimt(
            @RequestParam(name = "current") Integer current,
            @RequestParam(name = "size") Integer size,
            String modelName,
            Integer projectModelTypeId,
            @AuthenticationPrincipal UserDetails userDetails){
        PageVo<ModelShowVo> page = projectModelService.getModelByLimt(modelName,projectModelTypeId,size,current);
        if(page.getData().isEmpty()||page.getData().size()==0){
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_MODEL_LIMT_NO);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_MODEL_LIMT_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_MODEL_LIMT_OK.getMessage())
                .data(page)
                .build();

    }
}
