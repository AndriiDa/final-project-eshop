package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
  private CategoryRepository categoryRepository;
  private UserRepository userRepository;
  private ModelMapper modelMapper;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository, ModelMapper modelMapper) {
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  public List<CategoryDto> findAll(Map<String, String> allParams) {
    List<CategoryDto> result = new ArrayList<>();

    categoryRepository.findAll().forEach(category -> {
      result.add(modelMapper.map(category, CategoryDto.class));
    });

    List<CategoryDto> result0 = new ArrayList<>(result);
    Map<String, List<CategoryDto>> filterResults = new HashMap<>();

    for (String param : allParams.keySet()) {
      switch (param) {
        case ("code"):
          String code = String.valueOf(allParams.get(param));
          List<CategoryDto> result1 = result0
              .stream()
              .filter(category -> category.getCode().equalsIgnoreCase(code))
              .collect(Collectors.toList());
          if (!result1.isEmpty()) {
            filterResults.put(param, result1);
          }
          break;
        case ("name"):
          String name = String.valueOf(allParams.get(param));
          List<CategoryDto> result2 = result0
              .stream()
              .filter(category -> category.getName().equalsIgnoreCase(name))
              .collect(Collectors.toList());
          if (!result2.isEmpty()) {
            filterResults.put(param, result2);
          }
          break;
        case ("isgroup"):
          Boolean isGroup = Boolean.valueOf(allParams.get(param));
          List<CategoryDto> result3 = result0
              .stream()
              .filter(CategoryDto::isGroup)
              .collect(Collectors.toList());
          if (!result3.isEmpty()) {
            filterResults.put(param, result3);
          }
          break;
        case ("isactive"):
          Boolean isActive = Boolean.valueOf(allParams.get(param));
          List<CategoryDto> result4 = result0
              .stream()
              .filter(CategoryDto::isActive)
              .collect(Collectors.toList());
          if (!result4.isEmpty()) {
            filterResults.put(param, result4);
          }
          break;
        case ("parentcategoryid"):
          List<CategoryDto> result5;
          if (allParams.get(param).equalsIgnoreCase("null")) {
            result5 = result0
                .stream()
                .filter(category -> (category.getParentCategoryId() == null))
                .collect(Collectors.toList());
          } else {
            Long parentcategoryid = Long.valueOf(allParams.get(param));
            result5 = result0
                .stream()
                .filter(category -> category.getParentCategoryId().equals(parentcategoryid))
                .collect(Collectors.toList());
          }
          if (!result5.isEmpty()) {
            filterResults.put(param, result5);
          }
          break;
        default:
          break;
      }
    }

    result0.clear();
    int i = 0;
    for (String param : filterResults.keySet()) {
      if (i++ == 0) {
        result0.addAll(filterResults.get(param));
      } else {
        result0.retainAll(filterResults.get(param));
      }

    }
    return result0.size() == 0 ? result : result0;
  }

  public Long create(CategoryDto categoryDto) {
    Category category = modelMapper.map(categoryDto, Category.class);
    Long userId = categoryDto.getCrUserId();
    User crUser = userRepository.findById(userId).orElse(null);
    category.setCrUser(crUser);
    return modelMapper.map(categoryRepository.save(category), CategoryDto.class).getId();
  }

  public void save(Category category) {
    categoryRepository.save(category);
  }

  public CategoryDto findById(Long id) {
    Optional<Category> category = categoryRepository.findById(id);
    return category.map(category1 -> modelMapper.map(category1, CategoryDto.class)).orElse(null);
  }

  public int update(Long id, CategoryDto categoryDto) {
    if (findById(id) != null) {
      Category category = modelMapper.map(categoryDto, Category.class);
      Long crUserId = categoryDto.getCrUserId();
      Long lmUserId = categoryDto.getLmUserId();
      User crUser = userRepository.findById(crUserId).orElse(null);
      User lmUser = userRepository.findById(lmUserId).orElse(null);
      category.setCrUser(crUser);
      category.setLmUser(lmUser);
      category.setId(id);
      categoryRepository.save(category);
      return 1;
    } else {
      return 0;
    }
  }

  public int delete(Long id) {
    if (findById(id) != null) {
      categoryRepository.deleteById(id);
      return 1;
    } else {
      return 0;
    }
  }

  public List<PropertyDto> findPropertiesByCategoryId(Long id) {
    List<PropertyDto> result = new ArrayList<>();
    return result;
  }
}
