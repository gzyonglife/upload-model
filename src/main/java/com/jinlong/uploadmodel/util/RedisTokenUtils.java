package com.jinlong.uploadmodel.util;

import com.alibaba.fastjson.JSON;
import com.jinlong.uploadmodel.entity.access.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;

/**
 * @description: RedisTokenUtils
 * @program: upload-model
 * @author: jinlong
 * @time: 2020/6/1 15:53
 */
@Slf4j
public class RedisTokenUtils {
    private final HashOperations<String, String, String> redisHash;

    private final String accessKey;
    private final String hashKeyPrefix;

    public RedisTokenUtils(HashOperations<String, String, String> redisHash, String accessKey, String hashKeyPrefix) {
        this.redisHash = redisHash;
        this.accessKey = accessKey;
        this.hashKeyPrefix = hashKeyPrefix;
    }

    /**
     * 将 jwtUser存入redis
     *
     * @param userDetails
     * @return
     */
    public boolean pushJwtUser(UserDetails userDetails) {
        redisHash.put(accessKey, hashKeyPrefix + userDetails.getId(), JSON.toJSONString(userDetails));
        return true;
    }

    /**
     * 根据userId从redis获取jwtUser对象，当redis不存在时，返回null
     *
     * @param id
     * @return
     */
    public UserDetails pullJwtUserById(int id) {
        if (!hasKey(id))
            return null;
        String jwtUserJson = redisHash.get(accessKey, hashKeyPrefix + id);
        return JSON.parseObject(jwtUserJson, UserDetails.class);
    }

    /**
     * 检查是否存在该key
     *
     * @param id
     * @return
     */
    public boolean hasKey(int id) {
        return redisHash.hasKey(accessKey, hashKeyPrefix + id);
    }

    /**
     * 检查key是否过期
     *
     * @param id
     * @return
     */
    public boolean verifyExpire(int id) {
        if (!hasKey(id)) return true;
        UserDetails userDetails = pullJwtUserById(id);
        return userDetails.getExpirationTime() <= System.currentTimeMillis();
    }

    /**
     * 删除key
     *
     * @param id
     * @return
     */
    public boolean delKey(int id) {
        if (!hasKey(id)) return true;
        redisHash.delete(accessKey, hashKeyPrefix + id);
        return true;
    }
}
