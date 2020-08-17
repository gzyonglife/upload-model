//package com.jinlong.uploadmodel.controller;
//
//import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.constraints.NotNull;
//
///**
// * @description: ProjectDetailsController
// * @program: upload-model
// * @author: jinlong
// * @time: 2020/8/12 13:39
// */
//@RestController
//@RequestMapping("projectDetail")
//public class ProjectDetailsController {
//
//    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
//    @GetMapping("/getProjectDetail/projectId")
//    public ResponseEntity<?> getProjectDetailByProjectId(@RequestParam("projectId") @Validated @NotNull(message = "项目id不可为空") Integer projectId){
//
//    }
//}
