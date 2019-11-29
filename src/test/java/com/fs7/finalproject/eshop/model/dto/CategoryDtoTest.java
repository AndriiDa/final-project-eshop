package com.fs7.finalproject.eshop.model.dto;

import com.fs7.finalproject.eshop.model.Category;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

public class CategoryDtoTest {

  private static final ModelMapper modelMapper = new ModelMapper();

  @Test
  public void checkCategoryDtoToEntityMapping() {
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setName("Category DTO name");
    categoryDto.setDescription("Category DTO description");

    Category category = modelMapper.map(categoryDto, Category.class);
    assertEquals(categoryDto.getName(), category.getName());
    assertEquals(categoryDto.getDescription(), category.getDescription());
  }

  @Test
  public void checkCategoryEntityToDtoMapping() {
    Category category = new Category();
    category.setName("Category name");
    category.setDescription("Category description");
    category.setParentCategory(new Category());

    CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
    assertEquals(category.getName(), categoryDto.getName());
    assertEquals(category.getDescription(), categoryDto.getDescription());
    assertEquals(category.getParentCategory().getId(), categoryDto.getParentCategoryId());
  }

}