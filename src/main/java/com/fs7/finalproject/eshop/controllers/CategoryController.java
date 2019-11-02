package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    return ResponseEntity.ok(categoryService.findAll());
  }

}
