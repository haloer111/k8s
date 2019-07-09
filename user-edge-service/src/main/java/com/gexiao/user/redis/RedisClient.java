package com.gexiao.user.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 2017/10/30.
 */
@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final int DEFAULT_TIMEOUT = 3600;

    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public String getStr(String key) {
        return redisTemplate.opsForValue().get(key) == null ? "" : String.valueOf(redisTemplate.opsForValue().get(key));
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, int timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void setStr(String key, Object value, int timeout) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), timeout, TimeUnit.SECONDS);
    }

    public void setStr(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, JSONObject.toJSONString(value), DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    public void expire(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

}
