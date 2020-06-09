package com.seckill.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import com.seckill.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.seckill.model.Course;
import com.seckill.redis.CourseRedis;
import com.seckill.service.ICourseService;

import javax.transaction.Transactional;


@Service("courseService")
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseRedis courseRedis;

    public static final String ALL_COURSE_REDIS="allCourseRedis";

    @Override
    public List<Course> findAllCourses() {
        List<Course> courseList = new ArrayList<Course>();
        //redis 中读取数据
        String courseListString = (String) courseRedis.getString(ALL_COURSE_REDIS);
        //用JSON 把string 转化为list
        courseList = JSON.parseArray(courseListString, Course.class);
        //如果没有从redis中读到，从mysql中读取，再存到redis中
        if(StringUtils.isEmpty(courseListString)){
            //读数据库
            courseList = courseRepository.findAll();
            //缓存到redis中
            String coursesString = JSON.toJSONString(courseList);
            courseRedis.putString(ALL_COURSE_REDIS, coursesString, -1);
        }

        return courseList;
    }

    @Override
    public Course findCourseByCourseNo(String courseNo) {
        //Optional 是Java 8 中新特性，可以做空值的判断
        Optional<Course> course = courseRepository.findById(courseNo);
        // course.isPresent()?user.get():null
        return course.orElse(null);
    }

    @Override
    public int reduceStockByCourseNo(String courseNo) {
        log.info("CourseServiceImpl -> courseNo:{}", courseNo);
        return courseRepository.reduceStockByCourseNo(courseNo);
    }

}
