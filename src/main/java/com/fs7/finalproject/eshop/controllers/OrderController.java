package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
  private ProductService orderService;

  @Autowired
  public OrderController(ProductService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ResponseEntity<Page<ProductDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                                  Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(orderService.findAll(pageable))
        : ResponseEntity.ok(orderService.findAllByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto source) {
    return ResponseEntity.ok(orderService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(orderService.findById(id))
        ? ResponseEntity.ok(orderService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody ProductDto source) {
    return ResponseEntity.ok(orderService.update(id, source));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.deleteById(id));
  }
}
