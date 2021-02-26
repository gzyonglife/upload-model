package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.AdministrativeTableService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author gzy
 * @Date 2021/2/21 11:46
 * @Version 1.0
 */

@RestController
@RequestMapping("administrativeTable")
public class AdministrativeTableController {
    @Autowired
    AdministrativeTableService administrativeTableService;

    @GetMapping("/list")
    public ResponseEntity<?> getAdministrativeTable() {
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_ADMINISTRATIVETABLE_OK.getCode())
                .message(CustomResponseEnum.GET_ADMINISTRATIVETABLE_OK.getMessage())
                .data(administrativeTableService.getAdministrativeTable())
                .build();
    }
}
