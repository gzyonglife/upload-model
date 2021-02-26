package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectApprovalService;
import com.jinlong.uploadmodel.service.ProjectBeCompletedService;
import com.jinlong.uploadmodel.service.ProjectPublicityService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Author gzy
 * @Date 2021/2/26 10:35
 * @Version 1.0
 */

@RestController
@RequestMapping("ProjectAudit")
public class ProjectAuditProgressController {
    //项目竣工信息
    @Autowired
    ProjectBeCompletedService projectBeCompletedService;

    //项目公示信息
    @Autowired
    ProjectPublicityService projectPublicityService;

    //项目批准情况
    @Autowired
    ProjectApprovalService projectApprovalService;

    @GetMapping("/getProjectAudit")
    public ResponseEntity<?> getProjectAudit(@RequestParam("type") @NotNull String type,
                                             @RequestParam("projectId") @NotNull Integer projectId){
        if(type.equals("BeCompleted")){
            return ResponseEntity
                    .builder()
                    .data(projectBeCompletedService.getProjectBeCompletedById(projectId))
                    .code(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_OK.getCode())
                    .message(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_OK.getMessage())
                    .build();
        }else if(type.equals("Publicity")){
            return ResponseEntity
                    .builder()
                    .data(projectPublicityService.getProjectPublicityById(projectId))
                    .code(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_OK.getCode())
                    .message(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_OK.getMessage())
                    .build();
        }else if(type.equals("Approval")){
            return ResponseEntity
                    .builder()
                    .data(projectApprovalService.getProjectApprovalById(projectId))
                    .code(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_OK.getCode())
                    .message(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_OK.getMessage())
                    .build();
        }

        return ResponseEntity
                .builder()
                .data(null)
                .code(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_ERROR.getCode())
                .message(CustomResponseEnum.GET_PROJECTAUDIT_BY_TYPE_ERROR.getMessage())
                .build();
    }

}
