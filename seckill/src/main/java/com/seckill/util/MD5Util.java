package com.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static final String SALT = "springboot";

    private static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 模拟前端加密：对原始密码进行加盐加密
     * @param inputPass
     * @return
     */
    private static String inputToForm(String inputPass) {
        return md5(inputPass + SALT);

    }

    /**
     * 后端二次加密：对前端加密后的密码再加盐加密
     * @param formPass
     * @param dbSalt
     * @return
     */
    public static String formToDB(String formPass, String dbSalt) {
        return md5(dbSalt + formPass);
    }

    /**
     *对原始密码进行二重加密处理
     * @param inputPass
     * @param dbSalt
     * @return
     */
    public static String inputToDB(String inputPass, String dbSalt) {
        String formPass = inputToForm(inputPass);
        return formToDB(formPass, dbSalt);
    }

}
