package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.FirmTableService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author gzy
 * @Date 2021/1/19 14:37
 * @Version 1.0
 */

@RestController
@RequestMapping("firm")
public class FirmTableController {
    @Autowired
    FirmTableService firmTableService;

    @GetMapping("limt")
    public ResponseEntity<?> getFirmTableByLimt(String firmName,
                                                @RequestParam(name = "current", required = false)Integer current,
                                                @RequestParam(name = "size", required = false)Integer size){
        PageVo<FirmTable> pageVo = firmTableService.getFirmTableByLimt(firmName,current,size);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_STATISTICS_FIRM_LIMT_OK.getCode())
                .message(CustomResponseEnum.GET_STATISTICS_FIRM_LIMT_OK.getMessage())
                .data(pageVo)
                .build();
    }
}
