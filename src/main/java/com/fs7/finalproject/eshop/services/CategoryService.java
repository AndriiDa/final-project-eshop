package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.mapper.CategoryMapper;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CategoryService {
  private CategoryRepository categoryRepository;
  private UserRepository userRepository;
  private CategoryMapper mapper;

  @Autowired
  public CategoryService(CategoryRepository categoryRepository, UserRepository userRepository, CategoryMapper mapper) {
    this.categoryRepository = categoryRepository;
    this.userRepository = userRepository;
    this.mapper = mapper;
  }

  public Page<CategoryDto> findAll(Pageable pageable) {
    return categoryRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<CategoryDto> findAllByParams(Map<String, String> allParams, Pageable pageable) {
    // {parentid}{code}{name}{isgroup}{isactive}
    final String paramParentCategoryId = "parentid";
    Category template = new Category();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case paramParentCategoryId:
          if (String.valueOf(allParams.get(item)).equalsIgnoreCase("null")) {
            break;
          }
          Category parentCategory = new Category();
          parentCategory.setId(categoryRepository.getOne(Long.valueOf(allParams.get(item))).getId());
          template.setParentCategory(parentCategory);
          break;
        case "code":
          template.setCode(String.valueOf(allParams.get(item)));
          break;
        case "name":
          template.setName(String.valueOf(allParams.get(item)));
          break;
        case "isgroup":
          template.setIsGroup(Boolean.valueOf(allParams.get(item)));
          break;
        case "isactive":
          template.setIsActive(Boolean.valueOf(allParams.get(item)));
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<Category> example = Example.of(template, matcher);

    Page<Category> categoryFiltered = categoryRepository.findAll(example, pageable);

    // i need to handle ExampleMatcher above when parentCategoryId is null
    if (allParams.containsKey(paramParentCategoryId)
        && String.valueOf(allParams.get(paramParentCategoryId)).equalsIgnoreCase("null")) {

      List<Category> categoryWithParentCategoryNull = categoryRepository.findCategoriesByParentCategoryNull();
      if (categoryWithParentCategoryNull.size() > 0) {
        categoryWithParentCategoryNull.retainAll(categoryFiltered.getContent());
        return new PageImpl<>(categoryWithParentCategoryNull
            .stream()
            .map(item1 -> (mapper.toDto(item1)))
            .collect(Collectors.toList()), pageable, categoryWithParentCategoryNull.size());
      }
    }
    return categoryFiltered.map(item -> (mapper.toDto(item)));
  }

  public CategoryDto update(Long id, CategoryDto source) {
    Category destination = mapper.toEntity(source);
    return categoryRepository.findById(id)
        .map(item -> {
          item.setCode(destination.getCode());
          item.setName(destination.getName());
          item.setDescription(destination.getDescription());
          item.setImgUrl(destination.getImgUrl());
          item.setIsGroup(destination.getIsGroup());
          item.setIsActive(destination.getIsActive());
          Long categoryId = source.getParentCategoryId();
          Category category = Objects.isNull(categoryId)
              ? null
              : categoryRepository.findById(categoryId)
              .orElseThrow(() -> new ResourceNotFoundException("ParemtCategoryId " + categoryId
                  + ", specified in the request body json, - not found"));
          item.setParentCategory(category);
          Long crUserId = source.getCrUserId();
          Long lmUserId = source.getLmUserId();
          User crUser = userRepository.findById(crUserId)
              .orElseThrow(() -> new ResourceNotFoundException("crUserId " + crUserId
                  + ", specified in the request body json, - not found"));
          User lmUser = userRepository.findById(lmUserId)
              .orElseThrow(() -> new ResourceNotFoundException("lmUserId " + lmUserId
                  + ", specified in the request body json, - not found"));
          item.setCrUser(crUser);
          item.setLmUser(lmUser);
          item.setId(id);
          return mapper.toDto(categoryRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }

  public CategoryDto save(CategoryDto source) {
    return mapper.toDto(categoryRepository.save(mapper.toEntity(source)));
  }

  public Object findById(Long id) {
    return categoryRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }

  public ResponseEntity<?> deleteById(Long id) {
    return categoryRepository.findById(id).map(item -> {
      categoryRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }
}
