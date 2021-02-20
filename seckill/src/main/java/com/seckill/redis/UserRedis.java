package com.seckill.redis;

import com.seckill.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRedis extends BaseRedis<User> {

    private static final String REDIS_KEY = "com.seckill.redis.UserRedis";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }

}
