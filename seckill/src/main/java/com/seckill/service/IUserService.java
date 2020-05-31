package com.seckill.service;

import com.seckill.model.User;
import com.seckill.vo.UserVO;

public interface IUserService {

     User register(User user);

     UserVO getUser(String username);

     void saveUserToRedisByToken(UserVO dbUser, String token);

     Object getUserFromRedisByToken(String token);

}
