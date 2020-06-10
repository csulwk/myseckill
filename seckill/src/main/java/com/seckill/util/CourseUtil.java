package com.seckill.util;

import com.seckill.vo.CourseVO;
import com.seckill.model.Course;

/**
 * 将course对象转化为CourseVO
 * @author kai
 * @date 2020-6-10 23:58
 */
public class CourseUtil {

    private static final int COURSE_NOT_START = 0;
    private static final int COURSE_PROCESSING = 1;
    private static final int COURSE_COMPLETE = 2;

    public static CourseVO courseToCourseVO(Course course){
        CourseVO courseVO = new CourseVO();
        courseVO.setCourse(course);

        //课程状态 剩余时间
        long startTime = course.getStartTime().getTime();
        long endTime = course.getEndTime().getTime();
        long now = System.currentTimeMillis();

        int courseStatus = COURSE_NOT_START;
        int remainTime = 0;

        //课程还未开始
        if(now < startTime){
            remainTime = (int) ((startTime - now)/1000);
        }else if(now > endTime){
            //选课已经结束
            courseStatus = COURSE_COMPLETE;
            remainTime = -1;
        }else{
            //选课正在进行中
            courseStatus = COURSE_PROCESSING;
            remainTime = -1;
        }
        courseVO.setCourseStatus(courseStatus);
        courseVO.setRemainTime(remainTime);
        return courseVO;
    }
}
