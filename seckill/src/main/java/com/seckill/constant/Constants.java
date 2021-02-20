package com.seckill.constant;

/**
 * 常量类
 * @author kai
 * @date 2020-6-10 23:42
 */
public interface Constants {
    /**
     * 同一IP最大请求次数
     */
    int IP_REQUEST_MAX_NUM = 100;

    /**
     * 秒杀消息的topic名称
     */
    String SECOND_KILL_TOPIC_NAME = "second-kill";
}
