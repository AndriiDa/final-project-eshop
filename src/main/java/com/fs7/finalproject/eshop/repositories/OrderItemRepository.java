package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Order;
import com.fs7.finalproject.eshop.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

  Page<OrderItem> findOrderItemsByOrder(Order order, Pageable pageable);
  
  Optional<OrderItem> findOrderItemByIdAndOrder(Long id, Order order)
}