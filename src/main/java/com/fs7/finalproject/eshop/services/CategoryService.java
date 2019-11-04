package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> findAll() {
    List<Category> products = new ArrayList<>();
    categoryRepository.findAll().forEach(products::add);
    return products;
  }
}
