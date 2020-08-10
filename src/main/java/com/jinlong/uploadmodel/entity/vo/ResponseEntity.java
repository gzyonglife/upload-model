package com.jinlong.uploadmodel.entity.vo;

import com.jinlong.uploadmodel.util.CustomResponseEnum;
import lombok.Builder;
import lombok.Data;

/**
 * @description: BooleanVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/4 14:33
 */
@Data
@Builder
public class ResponseEntity<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResponseEntity<?> createFromEnum(CustomResponseEnum responseEnum) {
        return ResponseEntity
                .builder()
                .code(responseEnum.getCode())
                .message(responseEnum.getMessage())
                .build();
    }

}
