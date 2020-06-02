package com.jinlong.uploadmodel.entity.bo;

import lombok.Builder;
import lombok.Data;

/**
 * @description: UserBo
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 19:11
 */
@Data
@Builder
public class UserBo {
    private String userName;
    private String loginName;
    private String loginPassword;
    private Integer roleId;
    private Integer creatorId;
}
