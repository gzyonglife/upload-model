package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.data.ProjectPublicity;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectPublicityService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author gzy
 * @Date 2021/1/29 10:23
 * @Version 1.0
 */
@RestController
@RequestMapping("projectPublicity")
public class ProjectPublicityController {
    @Autowired
    ProjectPublicityService projectPublicityService;

    @GetMapping("getProjectPublicity")
    public ResponseEntity<?> getProjectPublicity(String projectName,
                                                @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                                                @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return ResponseEntity
                .builder()
                .data(projectPublicityService.getProjectPublicity(projectName,current,size))
                .code(CustomResponseEnum.GET_PROJECTPUBLICITY_LIKE_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECTPUBLICITY_LIKE_OK.getMessage())
                .build();

    }

    @PostMapping("updateProjectPublicity")
    public ResponseEntity<?> updateProjectPublicity(ProjectPublicity projectPublicity){
        if(!projectPublicityService.updateProjectPublicity(projectPublicity)){
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECTPUBLICITY_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.UPDATE_PROJECTPUBLICITY_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECTPUBLICITY_OK.getMessage())
                .build();
    }
}
