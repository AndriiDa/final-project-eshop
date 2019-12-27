package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.model.dto.ProductPropertyValueByProductDto;
import com.fs7.finalproject.eshop.services.ProductPropertyValueService;
import com.fs7.finalproject.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000")
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
  public ResponseEntity<Page<ProductDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                                  Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(productService.findAll(pageable))
        : ResponseEntity.ok(productService.findAllByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto source) {
    return ResponseEntity.ok(productService.save(source));
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping("/{id}")
  public ResponseEntity<ProductDto> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(productService.findById(id))
        ? ResponseEntity.ok(productService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/properties")
  public ResponseEntity<Page<ProductPropertyValueByProductDto>> findAllPropertiesByProductId(@Valid @PathVariable Long id,
                                                                                             Pageable pageable) {
    return Objects.nonNull(productPropertyValueService.findAllByProductId(id, pageable))
        ? ResponseEntity.ok(productPropertyValueService.findAllByProductId(id, pageable))
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody ProductDto source) {
    return ResponseEntity.ok(productService.update(id, source));
  }

  @PutMapping("/{id}/inactivate")
  public ResponseEntity<ProductDto> setInactive(@Valid @PathVariable("id") Long id) {
    return ResponseEntity.ok(productService.setInactive(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.deleteById(id));
  }
}
