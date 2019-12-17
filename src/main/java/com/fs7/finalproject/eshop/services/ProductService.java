package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.model.Category;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.User;
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

import java.util.Map;

@Service
public class ProductService {
  private ProductRepository productRepository;
  private UserRepository userRepository;
  private CategoryRepository categoryRepository;
  private BrandRepository brandRepository;
  private VendorRepository vendorRepository;
  private ProductMapper productMapper;

  @Autowired
  public ProductService(ProductRepository productRepository,
                        UserRepository userRepository,
                        CategoryRepository categoryRepository,
                        BrandRepository brandRepository,
                        VendorRepository vendorRepository,
                        ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.brandRepository = brandRepository;
    this.vendorRepository = vendorRepository;
    this.productMapper = productMapper;
  }

  public Page<ProductDto> findAll(Pageable pageable) {
    return productRepository.findAll(pageable).map(item -> (productMapper.toDto(item)));
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

    return productRepository.findAll(example, pageable).map(item -> (productMapper.toDto(item)));
  }

  public ProductDto update(Long id, ProductDto source) {
    Product destination = productMapper.toEntity(source);
    return productRepository.findById(id)
        .map(item -> {
          item.setSkuCode(destination.getSkuCode());
          item.setTitle(destination.getTitle());
          item.setDescription(destination.getDescription());
          item.setShortDescription(destination.getShortDescription());
          item.setLongDescription(destination.getLongDescription());
          item.setCartDescription(destination.getCartDescription());
          item.setUrlThumb(destination.getUrlThumb());
          item.setUrlImg(destination.getUrlImg());
          item.setWeight(destination.getWeight());
          item.setQuantity(destination.getQuantity());
          item.setBasePrice(destination.getBasePrice());
          item.setDiscountPrice(destination.getDiscountPrice());
          item.setIsOffer(destination.getIsOffer());
          item.setIsRecommended(destination.getIsRecommended());
          item.setIsActive(destination.getIsActive());
          Long categoryId = source.getCategoryId();
          Category category = categoryRepository.findById(categoryId)
              .orElseThrow(() -> new ResourceNotFoundException("CategoryId " + categoryId
                  + ", specified in the request body json, - not found"));
          item.setCategory(category);
          Long brandId = source.getBrandId();
          Brand brand = brandRepository.findById(brandId)
              .orElseThrow(() -> new ResourceNotFoundException("BrandId " + brandId
                  + ", specified in the request body json, - not found"));
          item.setBrand(brand);
          Long vendorId = source.getVendorId();
          Vendor vendor = vendorRepository.findById(vendorId)
              .orElseThrow(() -> new ResourceNotFoundException("VendorId " + vendorId
                  + ", specified in the request body json, - not found"));
          item.setVendor(vendor);
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
          return productMapper.toDto(productRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }

  public ProductDto save(ProductDto source) {
    return productMapper.toDto(productRepository.save(productMapper.toEntity(source)));
  }

  public Object findById(Long id) {
    return productRepository.findById(id)
        .map(item -> productMapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }

  public ResponseEntity<?> deleteById(Long id) {
    return productRepository.findById(id).map(item -> {
      productRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + id + " not found"));
  }
}

