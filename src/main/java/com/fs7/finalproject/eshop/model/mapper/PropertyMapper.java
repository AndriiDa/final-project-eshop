package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PropertyMapper extends AbstractMapper<Property, PropertyDto> {

  private final ModelMapper mapper;

  @Autowired
  public PropertyMapper(ModelMapper mapper) {
    super(Property.class, PropertyDto.class);
    this.mapper = mapper;
  }

  @PostConstruct
  public void setupMapper() {
    mapper.createTypeMap(Property.class, PropertyDto.class)
        //.addMappings(m -> m.skip(PropertyDto::setCrTime))
        //.addMappings(m -> m.skip(PropertyDto::setLmTime))
        .setPostConverter(toDtoConverter());
    mapper.createTypeMap(PropertyDto.class, Property.class)
        //.addMappings(m -> m.skip(Property::setCrTime))
        //.addMappings(m -> m.skip(Property::setLmTime))
        .setPostConverter(toEntityConverter());
  }

}
