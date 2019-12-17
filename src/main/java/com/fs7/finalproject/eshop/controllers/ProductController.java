package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.services.ProductPropertyValueService;
import com.fs7.finalproject.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
  private ProductService productService;
  private ProductPropertyValueService productPropertyValueService;

  @Autowired
  public ProductController(ProductService productService,
                           ProductPropertyValueService productPropertyValueService) {
    this.productService = productService;
    this.productPropertyValueService = productPropertyValueService;
  }

  @GetMapping
  public ResponseEntity<?> findAll(@RequestParam(required = false) Map<String, String> allParams, Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(productService.findAll(pageable))
        : ResponseEntity.ok(productService.findAllByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody ProductDto source) {
    return ResponseEntity.ok(productService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(productService.findById(id))
        ? ResponseEntity.ok(productService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/properties")
  public ResponseEntity<?> findAllPropertiesByProductId(@Valid @PathVariable Long id,
                                                        Pageable pageable) {
    return Objects.nonNull(productPropertyValueService.findAllByProductId(id, pageable))
        ? ResponseEntity.ok(productPropertyValueService.findAllByProductId(id, pageable))
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody ProductDto source) {
    return ResponseEntity.ok(productService.update(id, source));
  }

  @PutMapping("/{id}/inactivate")
  public ResponseEntity<?> setInactive(@Valid @PathVariable("id") Long id) {
    ProductDto destination = (ProductDto) productService.findById(id);
    destination.setIsActive(false);
    return ResponseEntity.ok(productService.save(destination));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.deleteById(id));
  }
}
