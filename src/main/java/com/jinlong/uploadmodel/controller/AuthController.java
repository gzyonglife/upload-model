package com.jinlong.uploadmodel.controller;

import com.jinlong.uploadmodel.config.security.UserDetailsServiceImpl;
import com.jinlong.uploadmodel.entity.access.UserDetails;
import com.jinlong.uploadmodel.entity.vo.JwtAuthenticationResponse;
import com.jinlong.uploadmodel.entity.vo.LoginVo;
import com.jinlong.uploadmodel.entity.vo.ResponseEntity;
import com.jinlong.uploadmodel.service.LoginLogService;
import com.jinlong.uploadmodel.util.CustomResponseEnum;
import com.jinlong.uploadmodel.util.IpUtils;
import com.jinlong.uploadmodel.util.JwtTokenProvider;
import com.jinlong.uploadmodel.util.RedisTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Value("${jwt.expirationInMs}")
    private int jwtExpirationInMs;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    RedisTokenUtils redisTokenUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody @Validated LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginVo.getUserName(),
                        loginVo.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        // 生成token
        String token = tokenProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        userDetails.setExpirationTime(System.currentTimeMillis() + jwtExpirationInMs);

        // 查看redis中是否有该对象
        if (!redisTokenUtils.hasKey(userDetails.getId())) {
            // 没有
            // 将 jwtUser存入redis
            redisTokenUtils.pushJwtUser(userDetails);
            // 查看是否过期
        } else if (redisTokenUtils.verifyExpire(userDetails.getId())) {
            // 过期
            redisTokenUtils.pushJwtUser(userDetails);
        }
        loginLogService.log(userDetails.getId(), IpUtils.getIpAddr(request));
        JwtAuthenticationResponse responseData = new JwtAuthenticationResponse(token, userDetails.getUsername(), userDetails.getRole());
        Cookie accessTokenCookie = new Cookie("AccessToken", responseData.getAccessToken());
        Cookie tokenTypeCookie = new Cookie("TokenType", responseData.getTokenType());

        response.addCookie(accessTokenCookie);
        response.addCookie(tokenTypeCookie);
        return ResponseEntity
                .builder()
                .code(CustomResponseEnum.LOGIN_OK.getCode())
                .message(CustomResponseEnum.LOGIN_OK.getMessage())
                .data(responseData)
                .build();
    }


}