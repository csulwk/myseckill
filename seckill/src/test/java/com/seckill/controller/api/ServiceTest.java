package com.seckill.controller.api;

import com.seckill.BaseMock;
import com.seckill.repository.CourseRepository;
import com.seckill.service.impl.CourseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

@Slf4j
public class ServiceTest extends BaseMock {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Before
    public void init() {
        log.info("init -> {}", "mock");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void serviceTest() {
        log.info("test -> {}", "service");
        int exp = 200;
        PowerMockito.when(courseRepository.reduceStockByCourseNo(Mockito.anyString())).thenReturn(exp);
        int act = courseService.reduceStockByCourseNo("test");
        log.info("exp: {}, act: {}", exp, act);
        Assert.assertEquals(exp, act);
    }
}
