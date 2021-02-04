package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.data.ProjectBeCompleted;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectBeCompletedService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author gzy
 * @Date 2021/1/29 9:23
 * @Version 1.0
 */
@RestController
@RequestMapping("projectBeCompleted")
public class ProjectBeCompletedController {
    @Autowired
    ProjectBeCompletedService projectBeCompletedService;

    @GetMapping("getProjectBeCompleted")
    public ResponseEntity<?> getProjectBeCompleted(String projectName,
                                                @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
                                                @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){

        return ResponseEntity
                .builder()
                .data(projectBeCompletedService.getProjectBeCompleted(projectName,current,size))
                .code(CustomResponseEnum.GET_PROJECTBECOMPLETED_LIKE_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECTBECOMPLETED_LIKE_OK.getMessage())
                .build();

    }

    @PostMapping("updateProjectBeCompleted")
    public ResponseEntity<?> updateProjectBeCompleted(@RequestBody @Validated ProjectBeCompleted projectBeCompleted){
        if(!projectBeCompletedService.updateProjectBeCompleted(projectBeCompleted)){
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECTBECOMPLETED_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.UPDATE_PROJECTBECOMPLETED_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECTBECOMPLETED_OK.getMessage())
                .build();
    }
}
