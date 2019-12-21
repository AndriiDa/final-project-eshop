package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.model.dto.BrandDto;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.model.dto.VendorDto;
import com.fs7.finalproject.eshop.model.mapper.BrandMapper;
import com.fs7.finalproject.eshop.model.mapper.ProductMapper;
import com.fs7.finalproject.eshop.repositories.BrandRepository;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
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
public class BrandService {
  private BrandRepository brandRepository;
  private UserRepository userRepository;
  private ProductRepository productRepository;
  private BrandMapper brandMapper;
  private ProductMapper productMapper;

  @Autowired
  public BrandService(BrandRepository brandRepository,
                      UserRepository userRepository,
                      ProductRepository productRepository,
                      BrandMapper brandMapper,
                      ProductMapper productMapper) {
    this.brandRepository = brandRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.brandMapper = brandMapper;
    this.productMapper = productMapper;
  }

  public Page<BrandDto> findAll(Pageable pageable) {
    return brandRepository.findAll(pageable).map(item -> (brandMapper.toDto(item)));
  }

  public Page<BrandDto> findByParams(Map<String, String> allParams, Pageable pageable) {
    // {name}{isactive}
    Brand template = new Brand();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "name":
          template.setName(String.valueOf(allParams.get(item)));
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

    Example<Brand> example = Example.of(template, matcher);

    return brandRepository.findAll(example, pageable).map(item -> (brandMapper.toDto(item)));
  }

  public BrandDto update(Long id, BrandDto source) {
    return brandRepository.findById(id)
        .map(item -> {
          Brand destination = (Brand) SerializationUtils
              .deserialize(SerializationUtils.serialize(brandMapper.toEntity(source)).clone());
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
          return brandMapper.toDto(brandRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }

  public BrandDto save(BrandDto source) {
    return brandMapper.toDto(brandRepository.save(brandMapper.toEntity(source)));
  }

  public BrandDto findById(Long id) {
    return brandRepository.findById(id)
        .map(item -> brandMapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }

  public Page<ProductDto> findAllProductsByBrandId(Long id, Pageable pageable) {
    return productRepository.findAllByBrand(brandMapper.toEntity((BrandDto) findById(id)), pageable)
        .map(item -> productMapper.toDto(item));
  }

  public BrandDto setInactive(Long id) {
    return brandRepository.findById(id)
        .map(item -> {
          item.setIsActive(false);
          return brandMapper.toDto(brandRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return brandRepository.findById(id).map(item -> {
      brandRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }
}
