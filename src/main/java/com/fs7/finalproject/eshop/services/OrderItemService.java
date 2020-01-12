package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.OrderItem;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.dto.OrderItemDto;
import com.fs7.finalproject.eshop.model.mapper.OrderItemMapper;
import com.fs7.finalproject.eshop.repositories.OrderItemRepository;
import com.fs7.finalproject.eshop.repositories.OrderRepository;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Map;

@Service
public class OrderItemService {
  private OrderItemRepository orderItemRepository;
  private OrderRepository orderRepository;
  private UserRepository userRepository;
  private ProductRepository productRepository;
  private OrderItemMapper mapper;

  @Autowired
  public OrderItemService(OrderItemRepository orderItemRepository,
                          OrderRepository orderRepository,
                          UserRepository userRepository,
                          ProductRepository productRepository,
                          OrderItemMapper mapper) {
    this.orderItemRepository = orderItemRepository;
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.mapper = mapper;
  }

  public Page<OrderItemDto> findAll(Pageable pageable) {
    return orderItemRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<OrderItemDto> findAllByOrderId(Long id, Pageable pageable) {
    return orderItemRepository.findOrderItemsByOrder(orderRepository.findById(id).get(), pageable)
        .map(item -> (mapper.toDto(item)));
  }

  public Page<OrderItemDto> findAllByParams(Map<String, String> allParams, Pageable pageable) {
    //{status}{productid}
    OrderItem template = new OrderItem();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "status":
          template.setStatus(String.valueOf(allParams.get(item)));
          break;
        case "productid":
          Product product = new Product();
          product.setId(productRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setProduct(product);
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<OrderItem> example = Example.of(template, matcher);

    return orderItemRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public OrderItemDto update(Long id, OrderItemDto source) {
    return orderItemRepository.findById(id)
        .map(item -> {
          OrderItem destination = (OrderItem) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
          destination.setCrUser(
              userRepository.findById(source.getCrUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "crUserId", source.getCrUserId()))
          );
          destination.setLmUser(
              userRepository.findById(source.getLmUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "lmUserId", source.getLmUserId()))
          );
          destination.setProduct(
              productRepository.findById(source.getProductId())
                  .orElseThrow(() ->
                      new ResourceNotFoundException("Product", "ProductId", source.getProductId()))
          );
          destination.setId(id);
          return mapper.toDto(orderItemRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "OrderItemId", id));
  }

  public OrderItemDto save(OrderItemDto source) {
    return mapper.toDto(orderItemRepository.save(mapper.toEntity(source)));
  }

  public OrderItemDto findById(Long id) {
    return orderItemRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("OrderItem", "OrderItemId", id));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return orderItemRepository.findById(id).map(item -> {
      orderItemRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("OrderItem", "OrderItemId", id));
  }
}

