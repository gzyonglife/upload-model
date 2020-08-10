package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.ProjectPlanTableDao;
import com.jinlong.uploadmodel.dao.ProjectTableDao;
import com.jinlong.uploadmodel.dao.UserTableDao;
import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.ProjectPlanTable;
import com.jinlong.uploadmodel.entity.data.ProjectTable;
import com.jinlong.uploadmodel.entity.data.UserTable;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.RegisterVo;
import com.jinlong.uploadmodel.entity.vo.UserVo;
import com.jinlong.uploadmodel.service.UserService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * @description: UserTableServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 19:16
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserTableDao userDao;

    @Autowired
    ProjectTableDao projectDao;

    @Autowired
    ProjectPlanTableDao projectPlanDao;


    @Override
    public boolean existLoginName(String loginName) {
        return userDao.selectCount(new QueryWrapper<UserTable>().eq("login_name", loginName).last("limit 1")) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo modifyUser(UserVo userVo, Integer id) {

        UserTable userTable;
        // 判断创建者与修改者是否为同一人
        if (!id.equals(userVo.getUserId())) {
            // 是创建者修改或超级管理员，修改所有信息
            log.info("创建者修改用户：修改者id：{},被修改者为：{}", id, userVo.getUserId());
            userTable = BeanBeanHelpUtils.copyProperties(userVo, UserTable.class);
            Assert.assertNotNull(userTable, CustomExceptionEnum.MODIFY_USER_ERROR);
//            判断是否本人修改
        } else {
            // 是本人修改 修改非权限信息
            log.info("{}修改个人信息", id);
            userTable = BeanBeanHelpUtils.copyProperties(userVo, UserTable.class);
            Assert.assertNotNull(userTable, CustomExceptionEnum.MODIFY_USER_ERROR);
            userTable.setRoleId(null);
        }
        if (userDao.updateById(userTable) == 1) {
            return BeanBeanHelpUtils.copyProperties(userTable, UserVo.class);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageVo<UserVo> getUserListOfPage(Integer id, PageVo pageVo) {

        Page<UserTable> tablePage = userDao.selectPage(new Page<>(pageVo.getCurrent(), pageVo.getSize()), new QueryWrapper<>());
        PageVo<UserVo> resultPage = new PageVo<>();
        resultPage.setCurrent(tablePage.getCurrent());
        resultPage.setSize(tablePage.getSize());
        resultPage.setTotal(tablePage.getTotal());
        resultPage.setData(BeanBeanHelpUtils.copyList(tablePage.getRecords(), UserVo.class));
        return resultPage;
    }

    /**
     * 判断该用户是否含有该项目的操作权限
     *
     * @param userDetails
     * @param projectId
     * @return
     */
    @Override
    public boolean hasProject(UserDetails userDetails, Integer projectId) {
        // 判断是否为超级管理员
        if (userDetails.hasRole("SUPERADMIN")) {
            return true;
        }
        // 查询数据库，判断是否拥有该项目权限
        return projectDao.selectCount(
                new QueryWrapper<ProjectTable>()
                        .eq("project_id", projectId)
                        .eq("user_id", userDetails.getId()))
                == 1;
    }

    /**
     * 判断该用户是否含有该计划实施的操作权限
     *
     * @param userDetails
     * @param projectPlanId
     * @return
     */
    @Override
    public boolean hasProjectPlan(UserDetails userDetails, Integer projectPlanId) {
        if (userDetails.hasRole("SUPERADMIN")) {
            return true;
        }
        // 获取项目计划实施表信息
        ProjectPlanTable projectPlanTable = projectPlanDao.selectById(projectPlanId);

        // 用户使用有项目操作权限
        return hasProject(userDetails, projectPlanTable.getProjectId());

    }

    /**
     * 判断当前用户是否有该用户修改权限
     *
     * @param userDetails
     * @param userId
     * @return
     */
    @Override
    public boolean hasUser(UserDetails userDetails, Integer userId) {
        if (userDetails.hasRole("SUPERADMIN")) {
            return true;
        }
        if (userDetails.equals(userId))
            return true;
        // 获取用户表数据
        UserTable userTable = userDao.selectById(userId);
        return userDetails.getId().equals(userTable.getCreatorId());
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param id
     * @return
     */
    @Override
    public Optional<UserVo> getUserById(Integer id) {
        UserTable userTable = userDao.selectById(id);
        UserVo userVo = BeanBeanHelpUtils.copyProperties(userTable, UserVo.class);
        return Optional.ofNullable(userVo);
    }

    /**
     * 新建用户
     *
     * @param userVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer create(RegisterVo userVo, Integer createId) {
        UserTable userTable = BeanBeanHelpUtils.copyProperties(userVo, UserTable.class);
        Assert.assertNotNull(userTable, CustomExceptionEnum.CREATE_USER_ERROR);
        userTable.setCreatorId(createId);
        userTable.setCreateTime(new Date());
        log.info("创建用户信息，userTable：{}", userTable);
        userDao.insert(userTable);
        return userTable.getUserId();
    }
}
