package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.FirmTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.FirmTableService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author gzy
 * @Date 2021/1/19 14:37
 * @Version 1.0
 */
@Slf4j
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

    @PostMapping("update")
    public ResponseEntity<?> updateFirmTable(@RequestBody @Validated FirmTable firmTable){
        if(!firmTableService.updateFirmTable(firmTable)){
            log.info("修改失败：",firmTable);
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_STATISTICS_FIRM_UPDATE_NO);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_STATISTICS_FIRM_UPDATE_OK.getCode())
                .message(CustomResponseEnum.GET_STATISTICS_FIRM_UPDATE_OK.getMessage())
                .data(true)
                .build();
    }

    @PostMapping("add")
    public ResponseEntity<?> addFirmTable(@RequestBody @Validated FirmTable firmTable){
        if(!firmTableService.addFirmTable(firmTable)){
            log.info("添加失败：",firmTable);
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_STATISTICS_FIRM_ADD_NO);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_STATISTICS_FIRM_ADD_OK.getCode())
                .message(CustomResponseEnum.GET_STATISTICS_FIRM_ADD_OK.getMessage())
                .data(true)
                .build();
    }

    @PostMapping("del")
    public ResponseEntity<?> delFirmTable(@RequestParam(name = "idList", required = false) List<Integer> idList,
                                          @AuthenticationPrincipal UserDetails userDetails){
        if(!firmTableService.delFirmTable(idList)){
            log.info("删除失败：",idList);
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_STATISTICS_FIRM_DEL_NO);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_STATISTICS_FIRM_DEL_OK.getCode())
                .message(CustomResponseEnum.GET_STATISTICS_FIRM_DEL_OK.getMessage())
                .data(true)
                .build();
    }
}
