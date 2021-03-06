package com.seckill.redis;


import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.alibaba.fastjson.JSON;

public abstract class BaseRedis<T> {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    @Resource
    protected  HashOperations<String, String, T> hashOperations;

    @Resource
    protected  ValueOperations<String, Object> valueOperations;

    protected abstract String getRedisKey();

    /**
     * 递减操作
     * @param key
     * @param by
     * @return
     */
    public double decr(String key, double by){
        //-by -> 减
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by){
        return redisTemplate.opsForValue().increment(key, by);
    }


    /**
     *  putString 1, 多一个flag isToString
     * @param key
     * @param domain
     * @param expire
     * @param isToString
     */
    public void putString(String key, Object domain, long expire, boolean isToString){
        if(isToString){
            String str = beanToString(domain);
            valueOperations.set(key, str);
        }else{
            valueOperations.set(key, domain);
        }
        if(expire != -1){
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * putString 2
     * @param key
     * @param domain
     * @param expire
     */
    public void putString(String key, Object domain, long expire){
        valueOperations.set(key, domain);
        if(expire != -1){
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }


    /**
     * getString 1
     * @param key
     * @param clazz
     * @return
     */
    public Object getString(String key, Class<T> clazz) {
        String str = (String) valueOperations.get(key);
        return stringToBean(str, clazz);
    }


    /**
     * getString 2
     * @param key
     * @return
     */
    public Object getString(String key) {
        return valueOperations.get(key);
    }


    public void put(String key, T domain, long expire){
        hashOperations.put(getRedisKey(), key, domain);
        if(expire != -1){
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 查询
     *
     * @param key 查询的key
     * @return
     */
    public T get(String key) {
        return hashOperations.get(getRedisKey(), key);
    }

    public static <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }



}
