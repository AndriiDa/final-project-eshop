package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.ProductPropertyValueDto;
import com.fs7.finalproject.eshop.services.ProductPropertyValueService;
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
@RequestMapping("/api/v1/productpropertyvalues")
public class ProductPropertyValueController {
  private ProductPropertyValueService productPropertyValueService;

  @Autowired
  public ProductPropertyValueController(ProductPropertyValueService productPropertyValueService) {
    this.productPropertyValueService = productPropertyValueService;
  }

  @GetMapping
  public ResponseEntity<?> findAll(@RequestParam(required = false) Map<String, String> allParams, Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(productPropertyValueService.findAll(pageable))
        : ResponseEntity.ok(productPropertyValueService.findAllByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody ProductPropertyValueDto source) {
    return ResponseEntity.ok(productPropertyValueService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(productPropertyValueService.findById(id))
        ? ResponseEntity.ok(productPropertyValueService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody ProductPropertyValueDto source) {
    return ResponseEntity.ok(productPropertyValueService.update(id, source));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(productPropertyValueService.deleteById(id));
  }
}
