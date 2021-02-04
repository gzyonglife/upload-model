package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.data.ProjectApproval;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectApprovalService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author gzy
 * @Date 2021/1/28 15:06
 * @Version 1.0
 */
@RestController
@RequestMapping("projectApproval")
public class ProjectApprovalController {
    @Autowired
    ProjectApprovalService projectApprovalService;

    @GetMapping("getProjectApproval")
    public ResponseEntity<?> getProjectApproval(String projectName,
                                                @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                                                @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return ResponseEntity
                .builder()
                .data(projectApprovalService.getProjectApproval(projectName,current,size))
                .code(CustomResponseEnum.GET_PROJECTAPPROVAL_LIKE_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECTAPPROVAL_LIKE_OK.getMessage())
                .build();

    }

    @PostMapping("updateProjectApproval")
    public ResponseEntity<?> updateProjectApproval(@RequestBody @Validated ProjectApproval projectApproval){
        if(!projectApprovalService.updateProjectApproval(projectApproval)){
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECTAPPROVAL_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.UPDATE_PROJECTAPPROVAL_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECTAPPROVAL_OK.getMessage())
                .build();
    }
}
