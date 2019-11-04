package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> findAll() {
    return ResponseEntity.ok(productService.findAll());
  }


}
