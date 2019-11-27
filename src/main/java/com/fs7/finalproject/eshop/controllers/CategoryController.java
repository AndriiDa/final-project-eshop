package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<CategoryDto>> findAll() {
    return ResponseEntity.ok(categoryService.findAll());
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
  public void createCategory(Category category) {
    categoryService.save(category);
  }

  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void updateCategory(Category category) {
    categoryService.save(category);
  }
}