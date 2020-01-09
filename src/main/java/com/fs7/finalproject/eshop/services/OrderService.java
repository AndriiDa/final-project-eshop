package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Order;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.OrderDto;
import com.fs7.finalproject.eshop.model.mapper.OrderMapper;
import com.fs7.finalproject.eshop.repositories.AddressRepository;
import com.fs7.finalproject.eshop.repositories.OrderRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

@Service
public class OrderService {
  private OrderRepository orderRepository;
  private UserRepository userRepository;
  private AddressRepository addressRepository;
  private OrderMapper mapper;

  @Autowired
  public OrderService(OrderRepository orderRepository,
                      UserRepository userRepository,
                      AddressRepository addressRepository,
                      OrderMapper mapper) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.addressRepository = addressRepository;
    this.mapper = mapper;
  }

  public Page<OrderDto> findAll(Pageable pageable) {
    return orderRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<OrderDto> findAllByParams(Map<String, String> allParams, Pageable pageable) {
    //{userid}{order_num}{order_date}{required_date}{receipt_name}
    //{tracking_number}{total}{status}
    // {productid} - ???
    Order template = new Order();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "userid":
          User user = new User();
          user.setId(userRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setUser(user);
          break;
        case "order_num":
          template.setOrderNum(String.valueOf(allParams.get(item)));
          break;
        case "order_date":
          template.setOrderDate(Date.valueOf(allParams.get(item)));
          break;
        case "required_date":
          template.setRequiredDate(Date.valueOf(allParams.get(item)));
          break;
        case "receipt_name":
          template.setReceiptName(String.valueOf(allParams.get(item)));
          break;
        case "tracking_number":
          template.setTrackingNumber(String.valueOf(allParams.get(item)));
          break;
        case "total":
          template.setTotal(BigDecimal.valueOf(Long.valueOf(allParams.get(item))));
          break;
        case "status":
          template.setStatus(String.valueOf(allParams.get(item)));
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<Order> example = Example.of(template, matcher);

    return orderRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public OrderDto update(Long id, OrderDto source) {
    return orderRepository.findById(id)
        .map(item -> {
          Order destination = (Order) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
          destination.setUser(
              userRepository.findById(source.getUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", source.getUserId()))
          );
          destination.setCrUser(
              userRepository.findById(source.getCrUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "crUserId", source.getCrUserId()))
          );
          destination.setLmUser(
              userRepository.findById(source.getLmUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "lmUserId", source.getLmUserId()))
          );
          destination.setReceiptAddress(
              addressRepository.findById(source.getReceiptAddressId())
                  .orElseThrow(() ->
                      new ResourceNotFoundException("Address", "ReceiptAddressId", source.getReceiptAddressId()))
          );
          destination.setId(id);
          return mapper.toDto(orderRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("Order", "OrderId", id));
  }

  public OrderDto save(OrderDto source) {
    return mapper.toDto(orderRepository.save(mapper.toEntity(source)));
  }

  public OrderDto findById(Long id) {
    return orderRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("Order", "OrderId", id));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return orderRepository.findById(id).map(item -> {
      orderRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("Order", "OrderId", id));
  }
}

