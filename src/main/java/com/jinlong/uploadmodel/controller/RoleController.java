package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.entity.vo.RoleVo;
import com.jinlong.uploadmodel.service.RoleService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getRoleList() {
        List<RoleVo> roleVoList = roleService.getRoleList();
        if (roleVoList.isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_ROLE_FAILURE);
        } else {
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.GET_ROLE_FAILURE.getCode())
                    .message(CustomResponseEnum.GET_ROLE_FAILURE.getMessage())
                    .data(roleVoList)
                    .build();
        }
    }

}
