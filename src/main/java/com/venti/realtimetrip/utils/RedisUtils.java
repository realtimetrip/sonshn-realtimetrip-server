package com.venti.realtimetrip.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public void setAuthCodeData(String key, String value, long expirationTime) {
        redisTemplate.opsForValue().set(key, value, expirationTime, TimeUnit.MILLISECONDS);
    }

    @Transactional(readOnly = true)
    public String getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Transactional
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

}
