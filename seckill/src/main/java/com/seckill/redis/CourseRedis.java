package com.seckill.redis;

import com.seckill.model.entity.Course;
import org.springframework.stereotype.Repository;


@Repository
public class CourseRedis extends BaseRedis<Course> {
    private static final String REDIS_KEY = "com.seckill.redis.CourseRedis";

    @Override
    protected String getRedisKey(){
        return REDIS_KEY;
    }
}
