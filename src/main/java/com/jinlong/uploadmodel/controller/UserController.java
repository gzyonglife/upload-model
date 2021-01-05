package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.PageVo;
import com.jinlong.uploadmodel.entity.vo.RegisterVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.entity.vo.UserVo;
import com.jinlong.uploadmodel.service.UserService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

;import java.util.Optional;

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
    UserService userService;


    /**
     * 新建账号
     *
     * @param registerVo
     * @param user
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @PostMapping("register")
    public ResponseEntity<?> register(
            @RequestBody @Validated RegisterVo registerVo,
            @AuthenticationPrincipal UserDetails user) {
        Integer newUserId = userService.create(registerVo, user.getId());
        if (newUserId != null && newUserId >= 0) {
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.CREATE_USER_OK.getCode())
                    .message(CustomResponseEnum.CREATE_USER_OK.getMessage())
                    .data(newUserId)
                    .build();
        }
        return ResponseEntity.createFromEnum(CustomResponseEnum.CREATE_USER_FAILURE);
    }

    /**
     * 检验用户名是否已存在
     *
     * @param loginName
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("existLoginName")
    public boolean existLoginName(@RequestParam @Validated @NotEmpty(message = "loginName不能为空") String loginName) {
        // 判断是否存在该用户名
        return userService.existLoginName(loginName);
    }

    /**
     * 修改用户
     *
     * @param userVo
     * @param userDetails
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN','guest')")
    @PutMapping("modifyUser/userId")
    public ResponseEntity<?> modifyUserById(
            @RequestParam @Validated UserVo userVo,
            @AuthenticationPrincipal UserDetails userDetails) {
        // 判断当前用户是否有修改权限
        if (!userService.hasUser(userDetails, userVo.getUserId())) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.AUTHORITY_INSUFFICIENT);
        }
        // 进行修改
        UserVo result = userService.modifyUser(userVo, userDetails.getId());

        // 判断是否修改成功
        if (result == null) {
            return ResponseEntity
                    .builder()
                    .code(CustomResponseEnum.CREATE_USER_FAILURE.getCode())
                    .message(CustomResponseEnum.CREATE_USER_FAILURE.getMessage())
                    .build();
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.CREATE_USER_OK.getCode())
                .message(CustomResponseEnum.CREATE_USER_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 获取用户列表
     *
     * @param user
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getUser/all")
    public ResponseEntity<?> getUserList(
            @RequestBody @Validated PageVo pageVo,
            @AuthenticationPrincipal UserDetails user) {
        PageVo<UserVo> result = userService.getUserListOfPage(user.getId(), pageVo);
        if (result.getData().isEmpty()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_USER_LIST_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_USER_LIST_OK.getCode())
                .message(CustomResponseEnum.GET_USER_LIST_OK.getMessage())
                .data(result)
                .build();
    }

    /**
     * 获取用户列表
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAnyAuthority('SUPERADMIN','ADMIN')")
    @GetMapping("getUser/id")
    public ResponseEntity<?> getUserById(
            @RequestParam @Validated @NotNull(message = "id不可为空") Integer id) {
        Optional<UserVo> result = userService.getUserById(id);
        if (!result.isPresent()) {
            return ResponseEntity.createFromEnum(CustomResponseEnum.GET_USER_FAILURE);
        }
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.GET_USER_OK.getCode())
                .message(CustomResponseEnum.GET_USER_OK.getMessage())
                .data(result.get())
                .build();
    }
}
