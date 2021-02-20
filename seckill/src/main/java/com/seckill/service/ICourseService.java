package com.seckill.service;

import java.util.List;

import com.seckill.model.entity.Course;

public interface ICourseService {

     List<Course> findAllCourses();

     Course findCourseByCourseNo(String courseNo);

     int reduceStockByCourseNo(String courseNo);

}
