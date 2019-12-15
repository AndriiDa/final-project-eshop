package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.BrandDto;
import com.fs7.finalproject.eshop.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
  private BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  public ResponseEntity<?> findAll(@RequestParam(required = false) Map<String, String> allParams, Pageable pageable) {
    String paramName = "name";
    String paramIsActive = "isactive";
    String paramNameValue = null;
    Boolean paramIsActiveValue = null;

    if (allParams.keySet().contains(paramName)) {
      paramNameValue = String.valueOf(allParams.get(paramName));
    }

    if (allParams.keySet().contains(paramIsActive)) {
      paramIsActiveValue = Boolean.valueOf(allParams.get(paramIsActive));
    }

    return allParams.isEmpty()
        ? ResponseEntity.ok(brandService.findAll(pageable))
        : ResponseEntity.ok(brandService.findByNameAndActiveIsTrue(paramNameValue, paramIsActiveValue, pageable));
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody BrandDto source) {
    return ResponseEntity.ok(brandService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(brandService.findById(id))
        ? ResponseEntity.ok(brandService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/products")
  public ResponseEntity<?> findAllProductsByBrandId(@Valid @PathVariable Long id, Pageable pageable) {
    return ResponseEntity.ok(brandService.findAllProductsByBrandId(id, pageable));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody BrandDto brandDto) {
    return ResponseEntity.ok(brandService.update(id, brandDto));
  }

  @PutMapping("/{id}/inactivate")
  public ResponseEntity<?> setInactive(@Valid @PathVariable("id") Long id) {
    BrandDto brand = (BrandDto) brandService.findById(id);
    brand.setIsActive(false);
    return ResponseEntity.ok(brandService.save(brand));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(brandService.deleteById(id));
  }
}
