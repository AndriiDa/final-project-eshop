package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.ProductPropertyValue;
import com.fs7.finalproject.eshop.model.PropertyValue;
import com.fs7.finalproject.eshop.model.dto.ProductPropertyValueByProductDto;
import com.fs7.finalproject.eshop.model.dto.ProductPropertyValueDto;
import com.fs7.finalproject.eshop.model.mapper.ProductPropertyValueByProductMapper;
import com.fs7.finalproject.eshop.model.mapper.ProductPropertyValueMapper;
import com.fs7.finalproject.eshop.repositories.ProductPropertyValueRepository;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.PropertyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Map;

@Service
public class ProductPropertyValueService {
  private ProductPropertyValueRepository productPropertyValueRepository;
  private ProductRepository productRepository;
  private PropertyValueRepository propertyValueRepository;
  private ProductPropertyValueMapper mapper;
  private ProductPropertyValueByProductMapper valueByProductMapper;

  @Autowired
  public ProductPropertyValueService(ProductPropertyValueRepository productPropertyValueRepository,
                                     ProductRepository productRepository,
                                     PropertyValueRepository propertyValueRepository,
                                     ProductPropertyValueMapper mapper,
                                     ProductPropertyValueByProductMapper valueByProductMapper) {
    this.productPropertyValueRepository = productPropertyValueRepository;
    this.productRepository = productRepository;
    this.propertyValueRepository = propertyValueRepository;
    this.mapper = mapper;
    this.valueByProductMapper = valueByProductMapper;
  }

  public Page<ProductPropertyValueDto> findAll(Pageable pageable) {
    return productPropertyValueRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<ProductPropertyValueDto> findAllByParams(Map<String, String> allParams, Pageable pageable) {
    // {productid}{valueid - short for propertyValueId}
    ProductPropertyValue template = new ProductPropertyValue();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "productid":
          Product product = new Product();
          product.setId(productRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setProduct(product);
          break;
        case "valueid":
          PropertyValue value = new PropertyValue();
          value.setId(propertyValueRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setPropertyValue(value);
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<ProductPropertyValue> example = Example.of(template, matcher);

    return productPropertyValueRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public ProductPropertyValueDto update(Long id, ProductPropertyValueDto source) {
    return productPropertyValueRepository.findById(id)
        .map(item -> {
          ProductPropertyValue destination = (ProductPropertyValue) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
          destination.setId(id);
          return mapper.toDto(productPropertyValueRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("ProductPropertyValueId " + id + " not found"));
  }

  public ProductPropertyValueDto save(ProductPropertyValueDto source) {
    return mapper.toDto(productPropertyValueRepository.save(mapper.toEntity(source)));
  }

  public ProductPropertyValueDto findById(Long id) {
    return productPropertyValueRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("ProductPropertyValueId " + id + " not found"));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return productPropertyValueRepository.findById(id).map(item -> {
      productPropertyValueRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("ProductPropertyValueId " + id + " not found"));
  }

  public Page<ProductPropertyValueByProductDto> findAllByProductId(Long id, Pageable pageable) {
    return productPropertyValueRepository.findAllByProduct(productRepository.getOne(id), pageable)
        .map(item -> (valueByProductMapper.toDto(item)));
  }
}

