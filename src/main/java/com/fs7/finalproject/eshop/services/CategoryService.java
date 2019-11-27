package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<CategoryDto> findAll() {
    List<CategoryDto> result = new ArrayList<>();
    categoryRepository.findAll().forEach(category -> {
      CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
      if (category.getCategory() == null) {
        result.add(categoryDto);
      }
    });
    return result;
  }

  public void save(Category category) {
    categoryRepository.save(category);
  }
}
