package com.jinlong.uploadmodel;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.UserTableDao;
import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.data.UserTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class UploadModelApplicationTests {

    @Autowired
    UserTableDao userDao;

    @Test
    void addAdminUser() {

//        UserTable userTable = new UserTable();
//        userTable.setLoginName("2");
//        userTable.setUserId(1);
//        userTable.setLoginPassword("123");
//        JwtUser jwtUser=new JwtUser(userTable,"AAA");

        String jwtUserJson = "{\"accountNonExpired\":true,\"accountNonLocked\":true,\"authorities\":[{\"authority\":\"SUPERADMIN\"}],\"credentialsNonExpired\":true,\"enabled\":true,\"id\":1,\"password\":\"$2a$10$Q96zmpvV4Udk81xT9ZfdbeF0zRezkHWiHt/jBIbG5391PObg2j35i\",\"username\":\"admin\"}";
        UserDetails userDetails = JSON.parseObject(jwtUserJson, UserDetails.class);
//        for (GrantedAuthority authority : jwtUser.getAuthorities()) {
//            System.out.println(authority.getAuthority());
//        }
        System.out.println(userDetails);
    }

    @Test
    void mybatisPageTest() {

        Page<UserTable> page = new Page<>(2, 5);
        Page<UserTable> userIPage = userDao.selectPage(page, new QueryWrapper<>());
        assertThat(page).isSameAs(userIPage);
        log.error("总条数 -------------> {}", userIPage.getTotal());
        log.error("当前页数 -----------> {}", userIPage.getCurrent());
        log.error("当前每页显示数 ------> {}", userIPage.getSize());
        List<UserTable> records = userIPage.getRecords();
        assertThat(records).isNotEmpty();
        records.forEach(i->System.out.println(i));
    }
}
