package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

  Optional<OrderItem> findOrderItemsById(Long id);

}