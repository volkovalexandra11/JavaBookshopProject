package com.bookshop.Bookshop.repos;


import com.bookshop.Bookshop.entities.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select order from Order order where order.user_id = ?1")
    List<Order> findAllByUser_id(Long user_id);
}