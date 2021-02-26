package com.jinlong.uploadmodel.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.RoleTableDao;
import com.jinlong.uploadmodel.dao.UserTableDao;
import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.RoleTable;
import com.jinlong.uploadmodel.entity.data.UserTable;
import com.jinlong.uploadmodel.util.Assert;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import com.jinlong.uploadmodel.util.RedisTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @description: CustomUserDetailsService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/28 12:30
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserTableDao userDao;

    @Autowired
    RedisTokenUtils redisTokenUtils;


    @Autowired
    RoleTableDao roleDao;

    @Value("${jwt.expirationInMs}")
    private int jwtExpirationInMs;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        log.info("登录用户为：{}", loginName);
        UserTable user = userDao.selectOne(new QueryWrapper<UserTable>().eq("login_name", loginName));
        // 用户不存在
        Assert.assertNotNull(user, CustomExceptionEnum.NOT_FIND);
        RoleTable role = roleDao.selectById(user.getRoleId());
        log.info("该用户权限为：{}", role.getRoleAccess());
        return new UserDetails(user, System.currentTimeMillis() + jwtExpirationInMs, role.getRoleAccess());
    }

    public org.springframework.security.core.userdetails.UserDetails loadUserById(int id) {
        if (redisTokenUtils.hasKey(id) || !redisTokenUtils.verifyExpire(id)) {
            // 有该key 且未过期
            UserDetails userDetails = redisTokenUtils.pullJwtUserById(id);
            // 校验对象是否存在
            //Assert.assertNotNull(userDetails, CustomExceptionEnum.TOKEN_ERROR);
            return userDetails;
        }
        return null;
    }
}
