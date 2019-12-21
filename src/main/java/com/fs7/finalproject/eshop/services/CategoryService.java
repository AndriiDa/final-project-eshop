package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Category;
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
import org.springframework.util.SerializationUtils;

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
    return categoryRepository.findById(id)
        .map(item -> {
          Category destination = (Category) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());

          destination.setParentCategory(
              Objects.isNull(source.getParentCategoryId())
                  ? null
                  : categoryRepository.findById(source.getParentCategoryId())
                  .orElseThrow(() -> new ResourceNotFoundException("ParemtCategoryId " + source.getParentCategoryId()
                      + ", specified in the request body json, - not found"))
          );
          destination.setCrUser(
              userRepository.findById(source.getCrUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("crUserId " + source.getCrUserId()
                      + ", specified in the request body json, - not found"))
          );
          destination.setLmUser(
              userRepository.findById(source.getLmUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("lmUserId " + source.getLmUserId()
                      + ", specified in the request body json, - not found"))
          );
          destination.setId(id);
          return mapper.toDto(categoryRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }

  public CategoryDto save(CategoryDto source) {
    return mapper.toDto(categoryRepository.save(mapper.toEntity(source)));
  }

  public CategoryDto findById(Long id) {
    return categoryRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }

  public CategoryDto setInactive(Long id) {
    return categoryRepository.findById(id)
        .map(item -> {
          item.setIsActive(false);
          return mapper.toDto(categoryRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return categoryRepository.findById(id).map(item -> {
      categoryRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("CategoryId " + id + " not found"));
  }
}
