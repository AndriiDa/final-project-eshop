package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.BrandDto;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
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

  public Page<BrandDto> findByNameAndActiveIsTrue(String paramNameValue, Boolean paramIsActiveValue, Pageable pageable) {

    Brand brand = new Brand();
    brand.setName(paramNameValue);
    brand.setIsActive(paramIsActiveValue);

    ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnoreCase("name");
    Example<Brand> example = Example.of(brand, matcher);

    return brandRepository.findAll(example, pageable).map(item -> (brandMapper.toDto(item)));
  }

  public BrandDto update(Long id, BrandDto source) {
    Brand toUpdate = brandMapper.toEntity(source);
    return brandRepository.findById(id)
        .map(item -> {
          item.setName(toUpdate.getName());
          item.setNotes(toUpdate.getNotes());
          item.setIsActive(toUpdate.getIsActive());
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
          return brandMapper.toDto(brandRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }

  public ResponseEntity<?> deleteById(Long id) {
    return brandRepository.findById(id).map(item -> {
      brandRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }

  public BrandDto save(BrandDto source) {
    return brandMapper.toDto(brandRepository.save(brandMapper.toEntity(source)));
  }

  public Object findById(Long id) {
    return brandRepository.findById(id)
        .map(item -> brandMapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("BrandId " + id + " not found"));
  }

  public Page<ProductDto> findAllProductsByBrandId(Long id, Pageable pageable) {
    return productRepository.findAllByBrand(brandMapper.toEntity((BrandDto) findById(id)), pageable)
        .map(item -> productMapper.toDto(item));
  }
}
