package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.ProductPropertyValue;
import com.fs7.finalproject.eshop.model.dto.ProductPropertyValueByProductDto;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.PropertyValueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProductPropertyValueByProductMapper
    extends AbstractMapper<ProductPropertyValue, ProductPropertyValueByProductDto> {

  private ProductRepository productRepository;
  private PropertyValueRepository propertyValueRepository;

  private ModelMapper mapper;

  @Autowired
  public ProductPropertyValueByProductMapper(ModelMapper mapper,
                                             ProductRepository productRepository,
                                             PropertyValueRepository propertyValueRepository) {
    super(ProductPropertyValue.class, ProductPropertyValueByProductDto.class);
    this.productRepository = productRepository;
    this.propertyValueRepository = propertyValueRepository;
    this.mapper = mapper;
  }

  private Long getPropertyId(ProductPropertyValue source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getProduct().getId();
  }

  private Long getPropertyValueId(ProductPropertyValue source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getPropertyValue().getId();
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(ProductPropertyValue.class, ProductPropertyValueByProductDto.class)
        .addMappings(m -> m.skip(ProductPropertyValueByProductDto::setProductId))
        .addMappings(m -> m.skip(ProductPropertyValueByProductDto::setPropertyValueId))
        .addMappings(m -> m.skip(ProductPropertyValueByProductDto::setPropertyValueName))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(ProductPropertyValueByProductDto.class, ProductPropertyValue.class)
        .addMappings(m -> m.skip(ProductPropertyValue::setProduct))
        .addMappings(m -> m.skip(ProductPropertyValue::setPropertyValue))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(ProductPropertyValue source, ProductPropertyValueByProductDto destination) {
    destination.setProductId(getPropertyId(source));
    destination.setPropertyValueId(getPropertyValueId(source));
    destination.setPropertyName(propertyValueRepository.getOne(source.getPropertyValue().getId()).getProperty().getName());
    destination.setPropertyValueName(propertyValueRepository.getOne(source.getPropertyValue().getId()).getName());
  }

  @Override
  void mapSpecificFields(ProductPropertyValueByProductDto source, ProductPropertyValue destination) {
    destination.setProduct(productRepository.findById(source.getProductId()).orElse(null));
    destination.setPropertyValue(propertyValueRepository.findById(source.getPropertyValueId()).orElse(null));
  }
}