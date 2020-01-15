package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.OrderDto;
import com.fs7.finalproject.eshop.model.dto.OrderItemDto;
import com.fs7.finalproject.eshop.services.OrderItemService;
import com.fs7.finalproject.eshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
  private OrderService orderService;
  private OrderItemService orderItemService;

  @Autowired
  public OrderController(OrderService orderService,
                         OrderItemService orderItemService) {
    this.orderService = orderService;
    this.orderItemService = orderItemService;
  }

  @GetMapping
  public ResponseEntity<Page<OrderDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                                Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(orderService.findAll(pageable))
        : ResponseEntity.ok(orderService.findAllByParams(allParams, pageable));
  }

  @GetMapping("/{id}/items")
  public ResponseEntity<Page<OrderItemDto>> findAllByOrderId(@Valid @PathVariable Long id,
                                                             Pageable pageable) {
    if (Objects.isNull(orderService.findById(id))) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(orderItemService.findAllByOrderId(id, pageable));
  }

  @PostMapping
  public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto source) {
    return ResponseEntity.ok(orderService.save(source));
  }

  @PostMapping("/{id}/items")
  public ResponseEntity<OrderItemDto> create(@Valid @PathVariable Long id, @Valid @RequestBody OrderItemDto source) {
    if (Objects.isNull(orderService.findById(id))) {
      return ResponseEntity.notFound().build();
    } else {
      source.setOrderId(id);
    }
    return ResponseEntity.ok(orderItemService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> findById(@Valid @PathVariable Long id) {

    return Objects.nonNull(orderService.findById(id))
        ? ResponseEntity.ok(orderService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{orderId}/items/{orderItemId}")
  public ResponseEntity<OrderItemDto> findById(@Valid @PathVariable Long orderId, @Valid @PathVariable Long orderItemId) {
    return Objects.nonNull(orderItemService.findOrderItemByIdAndOrder(orderItemId, orderId))
        ? ResponseEntity.ok(orderItemService.findOrderItemByIdAndOrder(orderItemId, orderId))
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<OrderDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody OrderDto source) {

    return ResponseEntity.ok(orderService.update(id, source));
  }

  @PutMapping("/{orderId}/items/{orderItemId}")
  public ResponseEntity<OrderItemDto> updateOrderItem(@Valid @PathVariable("orderId") Long orderId,
                                                      @Valid @PathVariable("orderItemId") Long orderItemId,
                                                      @Valid @RequestBody OrderItemDto source) {

    return ResponseEntity.ok(orderItemService.updateOrderItem(orderId, orderItemId, source));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.deleteById(id));
  }

  @DeleteMapping("/{orderId}/items/{orderItemId}")
  public ResponseEntity<Object> deleteOrderItemByIdAndOrderId(@Valid @PathVariable("orderId") Long orderId,
                                                    @Valid @PathVariable("orderItemId") Long orderItemId) {
    return ResponseEntity.ok(orderItemService.deleteOrderItemByIdAndOrderId(orderId, orderItemId));
  }
}
