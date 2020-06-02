package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.bo.UserBo;
import com.jinlong.uploadmodel.entity.vo.UserVo;

import java.util.List;

/**
 * @description: UserTableService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 19:15
 */
public interface UserTableService {
    /**
     * 新建用户
     *
     * @param userBo
     * @return
     */
    int create(UserBo userBo);

    /**
     * 判断该登录名是否存在
     *
     * @param loginName
     * @return
     */
    boolean existLoginName(String loginName);

    /**
     * 修改用户信息，并回调用户信息，根据修改者与被修改者关系修改不同信息
     *
     * @param userVo
     * @param id
     * @return
     */
    UserVo modifyUser(UserVo userVo, Integer id);

    /**
     * 获取用户列表
     *
     * @param id
     * @return
     */
    List<UserVo> getUserList(Integer id);
}
