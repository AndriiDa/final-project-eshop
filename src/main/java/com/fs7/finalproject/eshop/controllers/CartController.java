package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.CartDto;
import com.fs7.finalproject.eshop.services.CartService;
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

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
  private CartService cartService;

  @Autowired
  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<Page<CartDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                               Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(cartService.findAll(pageable))
        : ResponseEntity.ok(cartService.findByParams(allParams, pageable));
  }

  @GetMapping("/{loginname}")
  public ResponseEntity<Page<CartDto>> findAllByLoginName(@Valid @PathVariable String loginname,
                                                          Pageable pageable) {
    return ResponseEntity.ok(cartService.findAllByLoginName(loginname, pageable));
  }

  @PostMapping
  public ResponseEntity<CartDto> create(@Valid @RequestBody CartDto source) {
    return ResponseEntity.ok(cartService.save(source));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CartDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody CartDto source) {
    return ResponseEntity.ok(cartService.update(id, source));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(cartService.deleteById(id));
  }
}
