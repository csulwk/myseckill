package com.seckill.redis;

import org.springframework.stereotype.Repository;

import com.seckill.model.entity.Orders;

@Repository
public class SeckillRedis extends BaseRedis<Orders>{
    private static final String REDIS_KEY = "com.seckill.redis.SeckillRedis";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }

}
