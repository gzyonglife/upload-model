package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.vo.ProjectModelTypeVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectModelTypeService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: ProjectModelTypeController
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/11 11:07
 */
@RestController
@RequestMapping("projectModeType")
public class ProjectModelTypeController {

    @Autowired
    ProjectModelTypeService projectModelTypeService;

    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @ResponseBody
    @GetMapping("getProjectModelType/all")
    public ResponseEntity<?> getProjectModelTypeList() {
        List<ProjectModelTypeVo> result = projectModelTypeService.getProjectModelTypeList();
        if (result.isEmpty()) {
            // 未查询到数据
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_MODEL_TYPE_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_OK.getMessage())
                .data(result)
                .build();
    }
}
