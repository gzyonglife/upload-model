package com.jinlong.uploadmodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.UserTableDao;
import com.jinlong.uploadmodel.entity.bo.UserBo;
import com.jinlong.uploadmodel.entity.data.UserTable;
import com.jinlong.uploadmodel.entity.vo.UserVo;
import com.jinlong.uploadmodel.service.UserTableService;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.BeanBeanHelpUtils;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description: UserTableServiceImpl
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 19:16
 */
@Slf4j
@Service
public class UserTableServiceImpl implements UserTableService {

    @Autowired
    UserTableDao userDao;


    @Override
    public boolean existLoginName(String loginName) {
        return userDao.selectCount(new QueryWrapper<UserTable>().eq("login_name", loginName).last("limit 1")) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVo modifyUser(UserVo userVo, Integer id) {
        UserTable modifyUser = userDao.selectOne(new QueryWrapper<UserTable>()
                .eq("user_id", userVo.getUserId()));
        UserTable userTable;
        // 判断创建者与修改者是否为同一人
        if (id.equals(modifyUser.getCreatorId())) {
            // 是创建者修改，修改所有信息
            log.info("创建者修改用户：修改者id：{},被修改者为：{}", id, userVo.getUserId());
            userTable = BeanBeanHelpUtils.copyProperties(userVo, UserTable.class);
            Assert.assertNotNull(userTable, CustomExceptionEnum.MODIFY_USER_ERROR);
            // 添加创建者id
            userTable.setCreatorId(id);
//            判断是否本人修改
        } else if (id.equals(modifyUser.getUserId())) {
            // 是本人修改 修改非权限信息
            log.info("{}修改个人信息", id);
            userTable = BeanBeanHelpUtils.copyProperties(userVo, UserTable.class);
            Assert.assertNotNull(userTable, CustomExceptionEnum.MODIFY_USER_ERROR);
            userTable.setRoleId(modifyUser.getRoleId());
        } else {
            // 权限不足
            throw CustomExceptionEnum.AUTHORITY_ERROR.getException();
        }
        if (userDao.updateById(userTable) == 1) {
            return BeanBeanHelpUtils.copyProperties(userTable, UserVo.class);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserVo> getUserList(Integer id) {
        List<UserTable> userTables = userDao.selectList(new QueryWrapper<UserTable>().eq("creator_id", id));
        return BeanBeanHelpUtils.copyList(userTables, UserVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(UserBo userBo) {
        UserTable userTable = BeanBeanHelpUtils.copyProperties(userBo, UserTable.class);
        Assert.assertNotNull(userTable, CustomExceptionEnum.CREATE_USER_ERROR);
        userTable.setCreateTime(new Date());
        userTable.setUserZone(userTable.getLoginName() + "_zone");
        log.info("创建用户信息，userTable：{}", userTable);
        userDao.insert(userTable);
        return userTable.getUserId();
    }
}
