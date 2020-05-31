package com.seckill.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.seckill.model.Orders;

@Repository
public interface OrderRepository  extends JpaRepository<Orders, String>{

     Orders findByUsernameAndCourseNo(String username, String courseNo);

     List<Orders> findByUsername(String username, Sort sort);

}
