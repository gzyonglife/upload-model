package com.jinlong.uploadmodel.config.security;

import com.alibaba.fastjson.JSON;
import com.jinlong.uploadmodel.entity.vo.ExceptionResponse;
import com.jinlong.uploadmodel.util.CustomExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: SecurityAuthenticationEntryPoint
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 13:37
 */
@Slf4j
@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException authenticationException) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setCharacterEncoding("UTF-8");

        if (authenticationException instanceof UsernameNotFoundException || authenticationException instanceof BadCredentialsException) {
            log.error("用户名或密码错误,异常信息为：{}",authenticationException.getMessage());
            httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(CustomExceptionEnum.ACCOUNT_PASSWORD_ERROR.getException())));
        } else if (authenticationException instanceof DisabledException) {
            log.error("用户已被禁用,异常信息为：{}",authenticationException.getMessage());
            httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(CustomExceptionEnum.USER_DISABLE.getException())));
        } else if (authenticationException instanceof LockedException) {
            log.error("账户被锁定,异常信息为：{}",authenticationException.getMessage());
            httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(CustomExceptionEnum.ACCOUNT_DISABLE.getException())));
        } else if (authenticationException instanceof AccountExpiredException) {
            log.error("账户过期,异常信息为：{}",authenticationException.getMessage());
            httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(CustomExceptionEnum.ACCOUNT_OVERDUE.getException())));
        } else if (authenticationException instanceof CredentialsExpiredException) {
            log.error("证书过期,异常信息为：{}",authenticationException.getMessage());
            httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(CustomExceptionEnum.CREDENTIALS_OVERDUE.getException())));
        } else {
            log.error("token错误,异常信息为：{}",authenticationException.getMessage());
            httpServletResponse.getWriter().write(JSON.toJSONString(new ExceptionResponse(CustomExceptionEnum.TOKEN_ERROR.getException())));
        }
    }
}
