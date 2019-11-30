package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductDto>> findAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
    ProductDto productDto = productService.findById(id);
    if (productDto == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(productDto);
    }
  }

  @PostMapping
  public ResponseEntity<Long> create(@RequestBody ProductDto productDto) {
    return ResponseEntity.ok(productService.create(productDto));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
    return productService.update(id, productDto);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int delete(@PathVariable("id") Long id) {
    return productService.deleteById(id);
  }
}
