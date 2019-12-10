package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.PropertyValue;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.repositories.PropertyValueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PropertyMapper extends AbstractMapper<Property, PropertyDto> {

  private final ModelMapper mapper;
  private final PropertyValueRepository propertyValueRepository;

  @Autowired
  public PropertyMapper(ModelMapper mapper, PropertyValueRepository propertyValueRepository) {
    super(Property.class, PropertyDto.class);
    this.mapper = mapper;
    this.propertyValueRepository = propertyValueRepository;
  }

  private List<Long> getIds(Property source) {
    return Objects.isNull(source) || Objects.isNull(source.getPropertyValues())
        ? null
        : source.getPropertyValues().stream().map(PropertyValue::getId).collect(Collectors.toList());
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Property.class, PropertyDto.class)
        //.addMappings(m -> m.skip(PropertyDto::setCrTime))
        //.addMappings(m -> m.skip(PropertyDto::setLmTime))
        .addMappings(m -> m.skip(PropertyDto::setPropertyValuesIds))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(PropertyDto.class, Property.class)
        //.addMappings(m -> m.skip(Property::setCrTime))
        //.addMappings(m -> m.skip(Property::setLmTime))
        .addMappings(m -> m.skip(Property::setPropertyValues))
        .setPostConverter(toEntityConverter());
  }

  @Override
  public void mapSpecificFields(Property source, PropertyDto destination) {
    destination.setPropertyValuesIds(getIds(source));
  }

  @Override
  void mapSpecificFields(PropertyDto source, Property destination) {
    List<PropertyValue> propertyValues = new ArrayList<>();
    propertyValues = source.getPropertyValuesIds().stream().map(item -> {
      return propertyValueRepository.findById(item).get();
    }).collect(Collectors.toList());
    destination.setPropertyValues(propertyValues);
  }

}