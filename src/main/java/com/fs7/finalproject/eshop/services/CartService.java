package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Cart;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.CartDto;
import com.fs7.finalproject.eshop.model.mapper.CartMapper;
import com.fs7.finalproject.eshop.repositories.CartRepository;
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
public class CartService {
  private CartRepository cartRepository;
  private UserRepository userRepository;
  private ProductRepository productRepository;
  private CartMapper mapper;

  @Autowired
  public CartService(CartRepository cartRepository,
                     UserRepository userRepository,
                     ProductRepository productRepository,
                     CartMapper mapper) {
    this.cartRepository = cartRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.mapper = mapper;
  }

  public Page<CartDto> findAll(Pageable pageable) {
    return cartRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<CartDto> findByParams(Map<String, String> allParams, Pageable pageable) {
    // {userid}{productid}
    Cart template = new Cart();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "userid":
          User user = new User();
          user.setId(Long.valueOf(allParams.get(item)));
          template.setUser(user);
          break;
        case "productid":
          Product product = new Product();
          product.setId(Long.valueOf(allParams.get(item)));
          template.setProduct(product);
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnorePaths("quantity")
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<Cart> example = Example.of(template, matcher);

    return cartRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<CartDto> findAllByLoginName(String loginName, Pageable pageable) {
    return findAllByUserId(userRepository.findByLoginName(loginName)
        .orElseThrow(() -> new ResourceNotFoundException("User", "LoginName", loginName)).getId(), pageable);
  }

  public Page<CartDto> findAllByEmail(String email, Pageable pageable) {
    return findAllByUserId(userRepository.findByEmail(email)
        .orElseThrow(() -> new ResourceNotFoundException("User", "email", email)).getId(), pageable);
  }

  private Page<CartDto> findAllByUserId(Long userId, Pageable pageable) {
    userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));

    Cart template = new Cart();
    User user = new User();
    user.setId(userId);
    template.setUser(user);

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnorePaths("quantity")
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<Cart> example = Example.of(template, matcher);

    return cartRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public CartDto update(Long id, CartDto source) {
    return cartRepository.findById(id)
        .map(item -> {
          Cart destination = (Cart) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
          destination.setUser(
              userRepository.findById(source.getUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", source.getUserId()))
          );
          destination.setProduct(
              productRepository.findById(source.getProductId())
                  .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", source.getProductId()))
          );
          destination.setId(id);
          return mapper.toDto(cartRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("Cart", "CartId", id));
  }

  public CartDto save(CartDto source) {
    return mapper.toDto(cartRepository.save(mapper.toEntity(source)));
  }

  public CartDto findById(Long id) {
    return cartRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("Cart", "CartId", id));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return cartRepository.findById(id).map(item -> {
      cartRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("Cart", "CartId", id));
  }
}