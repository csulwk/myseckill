package com.seckill.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -5785185994389280644L;

    private String username;

    private String password;

    private long id;

    private String dbflag;

    private String repassword;

}
