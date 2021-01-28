package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.ProjectImplementation;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectImplementationService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;

/**
 * @Author gzy
 * @Date 2021/1/26 15:27
 * @Version 1.0
 */
@RestController
@RequestMapping("projectImplementation")
public class ProjectImplementationController {
    @Autowired
    ProjectImplementationService projectImplementationService;

    @PostMapping("add")
    public ResponseEntity<?> addProjectImplementation(
            @RequestBody @Validated ProjectImplementation projectImplementation){
        if(!projectImplementationService.addProjectImplementation(projectImplementation)){
            return ResponseEntity.createFromEnum(CustomResponseEnum.ADD_PROJECT_IMPLEMENTATION_NO);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.ADD_PROJECT_IMPLEMENTATION_OK.getCode())
                .message(CustomResponseEnum.ADD_PROJECT_IMPLEMENTATION_OK.getMessage())
                .data(true)
                .build();
    }

    @PostMapping("update")
    public ResponseEntity<?> updateProjectImplementation(
            @RequestBody @Validated ProjectImplementation projectImplementation){
        if(!projectImplementationService.updateProjectImplementation(projectImplementation)){
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECT_IMPLEMENTATION_NO);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.UPDATE_PROJECT_IMPLEMENTATION_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECT_IMPLEMENTATION_OK.getMessage())
                .data(true)
                .build();
    }

    /**
     * 批量删除实施信息
     *
     * @param idList
     * @param userDetails
     * @return
     */
    @PostMapping("del")
    public ResponseEntity<?> delPeojectAll(@RequestParam(name = "idList", required = false) List<Integer> idList,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        if (idList == null || idList.isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.DEL_PROJECT_LIST_FAILURE);
        }
        if (!projectImplementationService.delProjectImplementationAll(idList)) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.DEL_PROJECT_LIST_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.DEL_PROJECT_LIST_OK.getCode())
                .message(CustomResponseEnum.DEL_PROJECT_LIST_OK.getMessage())
                .build();
    }

    /**
     * 模糊查询
     *
     * @param current
     * @param size
     * @param projectName
     * @param implementationDate
     * @return
     */
    @PostMapping("fuzzyQuery")
    public ResponseEntity<?> getProjectImplementationByLimt(
            @RequestParam(name = "current") @Validated @NotNull(message = "current不为空") Integer current,
            @RequestParam(name = "size") @Validated @NotNull(message = "size不为空") Integer size,
            String projectName,
            String implementationDate) throws ParseException {
        return ResponseEntity
                .builder()
                .data(projectImplementationService.getProjectImplementationByLimt(current,size,projectName,implementationDate))
                .code(CustomResponseEnum.GET_PROJECT_IMPLEMENTATION_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_IMPLEMENTATION_OK.getMessage())
                .build();
    }
}
