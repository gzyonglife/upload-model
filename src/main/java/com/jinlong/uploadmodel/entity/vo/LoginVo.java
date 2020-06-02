package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description: LoginVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/26 15:32
 */
@Data
public class LoginVo {
    @NotEmpty(message = "userName不可为空")
    private String userName;
    @NotEmpty(message = "password不可为空")
    private String password;
}
