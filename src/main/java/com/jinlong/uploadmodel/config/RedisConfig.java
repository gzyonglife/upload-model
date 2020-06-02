package com.jinlong.uploadmodel.config;

import com.jinlong.uploadmodel.util.RedisTokenUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description: RedisConfig
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 15:54
 */
@Configuration
public class RedisConfig {

    @Value("${redis.accessKey}")
    String accessKey;

    @Value("${redis.hashKeyPrefix}")
    String hashKeyPrefix;

    @Bean
    public HashOperations<String, String, String> redisHash(RedisTemplate<String, String> redisTemplate) {
        return redisTemplate.opsForHash();
    }

    @Bean
    public RedisTokenUtils redisTokenUtils(HashOperations<String, String, String> redisHash) {
        return new RedisTokenUtils(redisHash, accessKey, hashKeyPrefix);
    }
}
