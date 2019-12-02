package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.PropertyValue;
import com.fs7.finalproject.eshop.model.dto.PropertyValueDto;
import com.fs7.finalproject.eshop.repositories.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class PropertyValueMapper extends AbstractMapper<PropertyValue, PropertyValueDto> {

  private final ModelMapper mapper;
  private final PropertyRepository propertyRepository;

  @Autowired
  public PropertyValueMapper(ModelMapper mapper, PropertyRepository propertyRepository) {
    super(PropertyValue.class, PropertyValueDto.class);
    this.mapper = mapper;
    this.propertyRepository = propertyRepository;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(PropertyValue.class, PropertyValueDto.class)
        .addMappings(m -> m.skip(PropertyValueDto::setPropertyId)).setPostConverter(toDtoConverter());
    mapper.createTypeMap(PropertyValueDto.class, PropertyValue.class)
        .addMappings(m -> m.skip(PropertyValue::setProperty)).setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(PropertyValue source, PropertyValueDto destination) {
    destination.setPropertyId(getId(source));
  }

  private Long getId(PropertyValue source) {
    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getProperty().getId();
  }

  @Override
  void mapSpecificFields(PropertyValueDto source, PropertyValue destination) {
    destination.setProperty(propertyRepository.findById(source.getPropertyId()).orElse(null));
  }
}
