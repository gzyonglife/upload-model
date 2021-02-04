package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.ProjectCategoryTable;
import com.jinlong.uploadmodel.entity.vo.ProjectCategoryVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.ProjectCategoryService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: ProjectCategoryController
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/4 20:05
 */
@RestController
@RequestMapping("projectCategory")
public class ProjectCategoryController {

    @Autowired
    ProjectCategoryService projectCategoryService;

    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getProjectCategory/all")
    public ResponseEntity<?> getProjectCategoryList() {
        List<ProjectCategoryVo> projectCategoryVoList = projectCategoryService.getProjectCategoryList();
        if (projectCategoryVoList.isEmpty()) {
            // 返回为空
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_FAILURE);
        }

        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_OK.getMessage())
                .data(projectCategoryVoList)
                .build();
    }

    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("add")
    public ResponseEntity<?> createProjectCategory(
            @RequestBody @Validated ProjectCategoryVo projectCategoryVo) {
        ProjectCategoryVo result = projectCategoryService.createProjectCategory(projectCategoryVo);
        if (result == null) {
            // 返回为空
            return ResponseEntity.createFromEnum(CustomResponseEnum.CREATE_PROJECT_CATEGORY_FAILURE);
        }

        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_PROJECT_CATEGORY_OK.getCode())
                .message(CustomResponseEnum.CREATE_PROJECT_CATEGORY_OK.getMessage())
                .data(result)
                .build();
    }

    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getProjectCategory/id")
    public ResponseEntity<?> getProjectCategoryById(@RequestParam @Validated @NotNull(message = "id不可为空") Integer id) {
        ProjectCategoryVo result = projectCategoryService.getProjectCategoryById(id);
        if (result == null) {
            // 返回空
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_CATEGORY_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_CATEGORY_OK.getMessage())
                .data(result)
                .build();
    }
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getProjectCategory/parentId")
    public ResponseEntity<?> getProjectCategoryByParentCategoryId(@RequestParam @Validated @NotNull(message = "id不可为空") Integer id) {
        List<ProjectCategoryVo> projectCategoryVos = projectCategoryService.getProjectCategoryByParentCategoryId(id);
        if (projectCategoryVos.isEmpty()) {
            // 返回空
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_PROJECT_CATEGORY_LIST_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_PROJECT_CATEGORY_OK.getCode())
                .message(CustomResponseEnum.GET_PROJECT_CATEGORY_OK.getMessage())
                .data(projectCategoryVos)
                .build();
    }

    /**
     * 批量删除分类
     *
     * @param idList
     * @param userDetails
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('SUPERADMIN')")
    @PostMapping("/del/projectCategory")
    public ResponseEntity<?> delProjectCategoryAll(@RequestParam(name = "idList", required = false) List<Integer> idList,
                                           @AuthenticationPrincipal UserDetails userDetails){
        if (idList == null || idList.size() <= 0) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.DEL_PROJECT_LIST_FAILURE);
        }
        if (!projectCategoryService.delProjectCategoryAll(idList)) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.DEL_PROJECTCATEGORY_LIST_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.DEL_PROJECTCATEGORY_LIST_OK.getCode())
                .message(CustomResponseEnum.DEL_PROJECTCATEGORY_LIST_OK.getMessage())
                .build();
    }

    @PostMapping("/update/projectCategory")
    public ResponseEntity<?> updateProjectCategory(@RequestBody @Validated ProjectCategoryTable projectCategoryTable, @AuthenticationPrincipal UserDetails userDetails){
        if(!projectCategoryService.updateProjectCategory(projectCategoryTable)){
            return ResponseEntity.createFromEnum(CustomResponseEnum.UPDATE_PROJECTCATEGORY_NO);
        }
        return ResponseEntity
                .builder()
                .data(true)
                .code(CustomResponseEnum.UPDATE_PROJECTCATEGORY_OK.getCode())
                .message(CustomResponseEnum.UPDATE_PROJECTCATEGORY_OK.getMessage())
                .build();

    }

}
