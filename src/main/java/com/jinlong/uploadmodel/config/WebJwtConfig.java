package com.jinlong.uploadmodel.config;

import com.jinlong.uploadmodel.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: WebJwtConfig
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 13:37
 */
@Configuration
public class WebJwtConfig {

    @Value("${jwt.secret}")
    String jwtSecret;

    @Value("${jwt.expirationInMs}")
    int jwtExpirationInMs;

    /**
     * jwt 工具类
     *
     * @return
     */
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(jwtSecret, jwtExpirationInMs);
    }
}
