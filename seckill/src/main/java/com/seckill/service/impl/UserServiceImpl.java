package com.seckill.service.impl;

import com.seckill.model.entity.User;
import com.seckill.redis.UserRedis;
import com.seckill.repository.UserRepository;
import com.seckill.service.IUserService;
import com.seckill.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * @author kai
 * @date 2020-6-10 23:12
 */
@Service("userService")
@Transactional(rollbackOn = Exception.class)
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRedis userRedis;

    @Override
    public User register(User user) {
        return userRepository.saveAndFlush(user);
    }

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

        log.info("===========================================");
        log.info("将用户信息放入redis之前，：" + "username="+user.getUsername()+";password="+user.getPassword() );
        log.info("===========================================");
        //将信息放入redis
        userRedis.put(token, user, 3600);
    }

    @Override
    public Object getUserFromRedisByToken(String token) {
        return userRedis.get(token);
    }
}
