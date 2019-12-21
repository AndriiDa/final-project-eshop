package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.services.CategoryService;
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
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<Page<CategoryDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                                   Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(categoryService.findAll(pageable))
        : ResponseEntity.ok(categoryService.findAllByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto source) {
    return ResponseEntity.ok(categoryService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(categoryService.findById(id))
        ? ResponseEntity.ok(categoryService.findById(id))
        : ResponseEntity.notFound().build();
  }

  //@GetMapping("/{id}/properties")
  //public ResponseEntity<Page<CategoryProperty>> findAllPropertiesByProductId(@Valid @PathVariable Long id,
  //                                                      Pageable pageable) {
  //  return Objects.nonNull(productPropertyValueService.findAllByProductId(id, pageable))
  //      ? ResponseEntity.ok(productPropertyValueService.findAllByProductId(id, pageable))
  //      : ResponseEntity.notFound().build();
  //}

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody CategoryDto source) {
    return ResponseEntity.ok(categoryService.update(id, source));
  }

  @PutMapping("/{id}/inactivate")
  public ResponseEntity<CategoryDto> setInactive(@Valid @PathVariable("id") Long id) {
    return ResponseEntity.ok(categoryService.setInactive(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.deleteById(id));
  }
}