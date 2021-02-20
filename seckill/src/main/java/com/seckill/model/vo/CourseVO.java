package com.seckill.model.vo;


import java.io.Serializable;

import com.seckill.model.entity.Course;
import lombok.Data;

@Data
public class CourseVO implements Serializable{
    private static final long serialVersionUID = 2637546614806439774L;

    private Course course;
    private int courseStatus = 0;
    /**
     * 距离选课还有多久
     */
    private int remainTime = 0;

}
