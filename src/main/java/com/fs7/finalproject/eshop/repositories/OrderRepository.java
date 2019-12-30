package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Order;
import com.fs7.finalproject.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findOrdersByUser(User user);

}