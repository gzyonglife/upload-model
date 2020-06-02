package com.jinlong.uploadmodel.entity.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * @description: RegisterVo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 18:51
 */
@Data
public class RegisterVo {
    @NotEmpty(message = "userName不能为空")
    private String userName;
    @NotEmpty(message = "loginName不能为空")
    private String loginName;
    @NotEmpty(message = "loginPassword不能为空")
    private String loginPassword;
    @NotNull(message = "roleId不能为空")
    private Integer roleId;
}
