package com.seckill.controller.api;

import com.seckill.base.controller.BaseApiController;
import com.seckill.base.result.Result;
import com.seckill.model.Course;
import com.seckill.service.ICourseService;
import com.seckill.util.CourseUtil;
import com.seckill.vo.CourseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CourseApiController extends BaseApiController {

    @Autowired
    private ICourseService courseService;

    /**
     * 获取所有的课程列表
     * @return Result<List<Course>>
     */
    @RequestMapping(value = "/courseList", method = RequestMethod.GET)
    public Result<List<Course>> courseList(){
        return Result.success(courseService.findAllCourses());
    }

    /**
     * 课程详情
     * @param courseNo courseNo
     * @return Result<CourseVO>
     */
    @RequestMapping(value = "/courseDetail/{courseNo}", method = RequestMethod.GET)
    public Result<CourseVO> courseDetail(@PathVariable String courseNo){
        return Result.success(CourseUtil.courseToCourseVO(courseService.findCourseByCourseNo(courseNo)));

    }
}
