package com.jinlong.uploadmodel.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jinlong.uploadmodel.dao.RoleTableDao;
import com.jinlong.uploadmodel.dao.UserTableDao;
import com.jinlong.uploadmodel.entity.dto.RoleTable;
import com.jinlong.uploadmodel.entity.dto.UserTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @description: CustomUserDetailsService
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/28 12:30
 */
@Slf4j
@Primary
@Component
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserTableDao userDao;

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Autowired
    RoleTableDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        int timeOut = 30;
        log.info("登录用户为：{}", loginName);
        UserTable user = userDao.selectOne(new QueryWrapper<UserTable>().eq("login_name", loginName));
        RoleTable role = roleDao.selectById(user.getRoleId());
        log.info("该用户权限为：{}", role.getRoleAccess());
        // 生成token
//        String token = UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(token, loginName, timeOut, TimeUnit.MINUTES);
//        log.info("token以及用户权限信息存入redis缓存，key:{},value:{},过期时间为：{} minutes", token, loginName, timeOut);
        return new User(loginName, user.getLoginPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(role.getRoleAccess()));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
