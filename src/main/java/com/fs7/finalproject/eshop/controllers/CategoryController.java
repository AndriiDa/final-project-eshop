package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> findAll(@RequestParam(required = false) Map<String,String> allParams) {
    return ResponseEntity.ok(categoryService.findAll(allParams));
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("id") Long id) {
    CategoryDto categoryDto = categoryService.findCategoryById(id);
    if (categoryDto == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(categoryDto);
    }
  }

  @PostMapping
  @PatchMapping
  public ResponseEntity<Long> createCategory(@RequestBody CategoryDto categoryDto) {
    return ResponseEntity.ok(categoryService.createCategory(categoryDto));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
    return categoryService.updateCategory(id, categoryDto);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int deleteCategory(@PathVariable("id") Long id) {
    return categoryService.deleteCategory(id);
  }
}