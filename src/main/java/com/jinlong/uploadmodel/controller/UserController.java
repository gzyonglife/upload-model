package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.dao.UserTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * @description: UserController
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/5/26 15:03
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserTableDao userTableDao;

    @PostMapping("register")
    public String doRegister(@RequestParam("loginname") String loginName, @RequestParam("password") String passWord) {
        return "register Ok";
    }
    @RolesAllowed("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    @GetMapping("noLoginTest")
    public String noLoginTest() {
        return "noLoginTest";
    }


    @GetMapping("loginTest")
    public String loginTest() {
        return "loginTest";
    }
}
