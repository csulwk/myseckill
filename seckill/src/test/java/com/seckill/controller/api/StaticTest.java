package com.seckill.controller.api;

import com.seckill.BaseMock;
import com.seckill.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

@Slf4j
public class StaticTest extends BaseMock {

    @PrepareForTest({UUIDUtil.class})
    @Test
    public void uuidTest(){
        log.info("test -> {}", "static");
        // mock UUID 类，使其 randomUUID() 方法返回刚刚 mock 的 uuid 对象
        String exp = "123";
        PowerMockito.mockStatic(UUIDUtil.class);
        PowerMockito.when(UUIDUtil.getUUID()).thenReturn(exp);
        String act = UUIDUtil.getUUID();
        log.info("exp: {}, act: {}", exp, act);
        Assert.assertEquals(exp, act);
    }
}
