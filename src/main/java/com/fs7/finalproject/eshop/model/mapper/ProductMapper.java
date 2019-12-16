package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.repositories.BrandRepository;
import com.fs7.finalproject.eshop.repositories.CategoryRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import com.fs7.finalproject.eshop.repositories.VendorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductDto> {

  private UserRepository userRepository;
  private CategoryRepository categoryRepository;
  private VendorRepository vendorRepository;
  private BrandRepository brandRepository;

  private ModelMapper mapper;

  @Autowired
  public ProductMapper(ModelMapper mapper,
                       UserRepository userRepository,
                       CategoryRepository categoryRepository,
                       VendorRepository vendorRepository,
                       BrandRepository brandRepository) {
    super(Product.class, ProductDto.class);
    this.userRepository = userRepository;
    this.categoryRepository = categoryRepository;
    this.brandRepository = brandRepository;
    this.vendorRepository = vendorRepository;
    this.mapper = mapper;
  }

  private Long getCrUserId(Product source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCrUser().getId();
  }

  private Long getLmUserId(Product source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLmUser().getId();
  }

  private Long getBrandId(Product source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getBrand().getId()) ? null : source.getBrand().getId();
  }

  private Long getVendorId(Product source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getVendor().getId()) ? null : source.getVendor().getId();
  }

  private Long getCategoryId(Product source) {
    return Objects.isNull(source)
        || Objects.isNull(source.getCategory().getId()) ? null : source.getCategory().getId();
  }


  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Product.class, ProductDto.class)
        .addMappings(m -> m.skip(ProductDto::setCrUserId))
        .addMappings(m -> m.skip(ProductDto::setLmUserId))
        .addMappings(m -> m.skip(ProductDto::setCategoryId))
        .addMappings(m -> m.skip(ProductDto::setBrandId))
        .addMappings(m -> m.skip(ProductDto::setVendorId))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(ProductDto.class, Product.class)
        .addMappings(m -> m.skip(Product::setCrUser))
        .addMappings(m -> m.skip(Product::setLmUser))
        .addMappings(m -> m.skip(Product::setCategory))
        .addMappings(m -> m.skip(Product::setBrand))
        .addMappings(m -> m.skip(Product::setVendor))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Product source, ProductDto destination) {
    destination.setCrUserId(getCrUserId(source));
    destination.setLmUserId(getLmUserId(source));
    destination.setCategoryId(getCategoryId(source));
    destination.setBrandId(getBrandId(source));
    destination.setVendorId(getVendorId(source));
  }

  @Override
  void mapSpecificFields(ProductDto source, Product destination) {
    destination.setCrUser(userRepository.findById(source.getCrUserId()).orElse(null));
    destination.setLmUser(userRepository.findById(source.getLmUserId()).orElse(null));
    destination.setCategory(categoryRepository.findById(source.getCategoryId()).orElse(null));
    destination.setBrand(brandRepository.findById(source.getBrandId()).orElse(null));
    destination.setVendor(vendorRepository.findById(source.getVendorId()).orElse(null));
  }
}