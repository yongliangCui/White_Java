package com.tommy.white_jotter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;


    public void set(String key,Object value,long time){
        redisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }
    public void set(String key,Object value){
        redisTemplate.opsForValue().set(key, value);
    }
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public Boolean delete(String key){
        return redisTemplate.delete(key);
    }
    public Long delete(Set<String> keys){
        return redisTemplate.delete(keys);
    }
    public Set<String> getKeysByPattern(String pattern){
        return redisTemplate.keys(pattern);
    }
}
