package com.jinlong.uploadmodel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jinlong.uploadmodel.dao.UserTableDao;
import com.jinlong.uploadmodel.entity.data.UserTable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class UploadModelApplicationTests {

    @Autowired
    UserTableDao userDao;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Test
    void mybatisPageTest() {
        Page<UserTable> page = new Page<>(3, 5);
        Page<UserTable> userIPage = userDao.selectPage(page, new QueryWrapper<>());
        assertThat(page).isSameAs(userIPage);
        log.error("总条数 -------------> {}", userIPage.getTotal());
        log.error("当前页数 -----------> {}", userIPage.getCurrent());
        log.error("当前每页显示数 ------> {}", userIPage.getSize());
        List<UserTable> records = userIPage.getRecords();
        assertThat(records).isNotEmpty();
        records.forEach(i -> System.out.println(i));
    }

    @Test
    void m1() {
        String name = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        System.out.println(passwordEncoder.encode(name));
        System.out.println(passwordEncoder.encode(password));
    }

    @Test
    void m2() {
        String A = "b/index.gm_source_def.xml";
        System.out.println(A.substring(A.lastIndexOf("/")+1));
    }
}
