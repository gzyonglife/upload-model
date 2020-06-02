package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.bo.UserBo;
import com.jinlong.uploadmodel.entity.vo.RegisterVo;
import com.jinlong.uploadmodel.entity.vo.UserVo;
import com.jinlong.uploadmodel.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
    UserTableService userService;


    /**
     * 新建账号
     *
     * @param registerVo
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Validated RegisterVo registerVo, @AuthenticationPrincipal UserDetails user) {
        UserBo userBo = UserBo
                .builder()
                .userName(registerVo.getUserName())
                .loginName(registerVo.getLoginName())
                .loginPassword(registerVo.getLoginPassword())
                .roleId(registerVo.getRoleId())
                .creatorId(user.getId())
                .build();
        return ResponseEntity.ok(userService.create(userBo));
    }

    /**
     * 检验用户名是否已存在
     *
     * @param loginName
     * @return
     */
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("existLoginName")
    public boolean existLoginName(@RequestParam @Validated @NotEmpty(message = "loginName不能为空") String loginName) {
        // 判断是否存在该用户名
        return userService.existLoginName(loginName);
    }

    /**
     * 修改用户
     *
     * @param userVo
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('SUPERADMIN','ADMIN','guest')")
    @PutMapping("modifyUser/userId")
    public ResponseEntity<UserVo> modifyUserById(@RequestParam @Validated UserVo userVo, @AuthenticationPrincipal UserDetails user) {
        // 判断是否修改成功
        UserVo result = userService.modifyUser(userVo, user.getId());
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    /**
     * 获取用户列表
     * @param user
     * @return
     */
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("getUser/all")
    public ResponseEntity<List<UserVo>> getUserList(@AuthenticationPrincipal UserDetails user) {

        List<UserVo> result = userService.getUserList(user.getId());
        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }
}
