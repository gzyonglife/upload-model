package com.jinlong.uploadmodel.service;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.RegisterVo;
import com.jinlong.uploadmodel.entity.vo.UserVo;

import java.util.Optional;

/**
 * @description: UserTableService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 19:15
 */
public interface UserService {
    /**
     * 新建用户
     *
     * @param userVo
     * @param createId
     * @return
     */
    Integer create(RegisterVo userVo, Integer createId);

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
     * @param pageVo
     * @return
     */
    PageVo<UserVo> getUserListOfPage(Integer id, PageVo pageVo);

    /**
     * 判断该用户是否含有该项目的操作权限
     *
     * @param userDetails
     * @param projectId
     * @return
     */
    boolean hasProject(UserDetails userDetails, Integer projectId);

    /**
     * 判断该用户是否含有该计划实施的操作权限
     *
     * @param userDetails
     * @param projectPlanId
     * @return
     */
    boolean hasProjectPlan(UserDetails userDetails, Integer projectPlanId);

    /**
     * 判断当前用户是否有该用户修改权限
     *
     * @param userDetails
     * @param userId
     * @return
     */
    boolean hasUser(UserDetails userDetails, Integer userId);

    /**
     * 根据用户id获取用户信息
     *
     * @param id
     * @return
     */
    Optional<UserVo> getUserById(Integer id);
}
