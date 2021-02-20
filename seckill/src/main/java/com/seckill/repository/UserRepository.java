package com.seckill.repository;

import com.seckill.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUsernameAndPassword(String username,String Password);

    public User findByUsername(String username);

}
