package com.seckill.service;

import com.seckill.model.entity.User;
import com.seckill.model.vo.UserVO;

public interface IUserService {

     User register(User user);

     UserVO getUser(String username);

     void saveUserToRedisByToken(UserVO dbUser, String token);

    /**
     * getUserFromRedisByToken
     * @param token token
     * @return Object
     */
     Object getUserFromRedisByToken(String token);

}
