package com.seckill.service.impl;

import com.seckill.model.User;
import com.seckill.redis.UserRedis;
import com.seckill.repository.UserRepository;
import com.seckill.service.IUserService;
import com.seckill.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedis userRedis;

    @Override
    public User register(User user) {
        return userRepository.saveAndFlush(user);
    }

//    @Override
//    public User getUser(String username) {
//        return userRepository.getOne(username);
//    }

    @Override
    public UserVO getUser(String username) {
        UserVO userVO = new UserVO();
        User user = userRedis.get("username");
        if (user == null) {
            user = userRepository.findByUsername(username);
            if (user != null) {
                userRedis.put(user.getUsername(), user, -1);

            }else {
                //还为空 可能是没有注册
                return null;
            }
        }

        //将user 转化为 uservo对象
        BeanUtils.copyProperties(user, userVO);

        return userVO;
    }


    @Override
    public void saveUserToRedisByToken(UserVO dbUser, String token){

        User user = new User();
        BeanUtils.copyProperties(dbUser, user);


        System.out.println("===========================================");
        System.out.println("将用户信息放入redis之前，：" + "username="+user.getUsername()+";password="+user.getPassword() );
        System.out.println("===========================================");
        //将信息放入redis
        userRedis.put(token, user, 3600);
    }


    @Override
    public Object getUserFromRedisByToken(String token) {
        return userRedis.get(token);


    }
}
