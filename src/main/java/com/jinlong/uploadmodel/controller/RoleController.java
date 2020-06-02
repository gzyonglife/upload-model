package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.vo.RoleVo;
import com.jinlong.uploadmodel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: RoleController
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/2 19:33
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("getRole/all")
    public ResponseEntity<List<RoleVo>> getRoleList() {
        List<RoleVo> roleVoList = roleService.getRoleList();
        if (roleVoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(roleVoList);
        }
    }

}
