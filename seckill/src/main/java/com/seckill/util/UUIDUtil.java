package com.seckill.util;

import java.util.Random;
import java.util.UUID;

public class UUIDUtil {

    private static final int LENGTH = 6;
    /**
     * 生成32位UUID
     * @return String
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成6位随机数字
     * @return String
     */
    public static String randomNumber() {
        StringBuilder sb = new StringBuilder();
        Random r = new Random();
        for(int i = 1; i <= LENGTH; i++){
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }
}
