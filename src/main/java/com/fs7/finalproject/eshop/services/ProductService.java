package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.model.mapper.ProductMapper;
import com.fs7.finalproject.eshop.repositories.BrandRepository;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import com.fs7.finalproject.eshop.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.Map;
import java.util.Objects;

@Service
public class ProductService {
  private ProductRepository productRepository;
  private UserRepository userRepository;
  private CategoryRepository categoryRepository;
  private BrandRepository brandRepository;
  private VendorRepository vendorRepository;
  private ProductMapper mapper;

  @Autowired
  public ProductService(ProductRepository productRepository,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository,
                        BrandRepository brandRepository,
                        VendorRepository vendorRepository,
                        ProductMapper mapper) {
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.brandRepository = brandRepository;
    this.vendorRepository = vendorRepository;
    this.mapper = mapper;
  }

  public Page<ProductDto> findAll(Pageable pageable) {
    return productRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<ProductDto> findAllByParams(Map<String, String> allParams, Pageable pageable) {
    // {categoryid}{vendorid}{brandid}{sku}{isoffer}{isrecommend}{isactive}
    Product template = new Product();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "categoryid":
          Category category = new Category();
          category.setId(categoryRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setCategory(category);
          break;
        case "brandid":
          Brand brand = new Brand();
          brand.setId(brandRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setBrand(brand);
          break;
        case "vendorid":
          Vendor vendor = new Vendor();
          vendor.setId(vendorRepository
              .getOne(Long.valueOf(allParams.get(item))).getId());
          template.setVendor(vendor);
          break;
        case "sku":
          template.setSkuCode(allParams.get(item));
          break;
        case "isoffer":
          template.setIsOffer(Boolean.valueOf(allParams.get(item)));
          break;
        case "isrecommended":
          template.setIsRecommended(Boolean.valueOf(allParams.get(item)));
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

    Example<Product> example = Example.of(template, matcher);

    return productRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public ProductDto update(Long id, ProductDto source) {
    return productRepository.findById(id)
        .map(item -> {
          Product destination = (Product) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
          destination.setCategory(
              Objects.isNull(source.getCategoryId())
                  ? null
                  : categoryRepository.findById(source.getCategoryId())
                  .orElseThrow(() -> new ResourceNotFoundException("CategoryId " + source.getCategoryId()
                      + ", specified in the request body json, - not found"))
          );
          destination.setBrand(
              Objects.isNull(source.getBrandId())
                  ? null
                  : brandRepository.findById(source.getBrandId())
                  .orElseThrow(() -> new ResourceNotFoundException("BrandId " + source.getBrandId()
                      + ", specified in the request body json, - not found"))
          );
          destination.setVendor(
              Objects.isNull(source.getVendorId())
                  ? null
                  : vendorRepository.findById(source.getVendorId())
                  .orElseThrow(() -> new ResourceNotFoundException("VendorId " + source.getVendorId()
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
          return mapper.toDto(productRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }

  public ProductDto save(ProductDto source) {
    return mapper.toDto(productRepository.save(mapper.toEntity(source)));
  }

  public ProductDto findById(Long id) {
    return productRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }

  public ProductDto setInactive(Long id) {
    return productRepository.findById(id)
        .map(item -> {
          item.setIsActive(false);
          return mapper.toDto(productRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return productRepository.findById(id).map(item -> {
      productRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }
}

