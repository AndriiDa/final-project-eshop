package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.repositories.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
  private PropertyRepository propertyRepository;
  private ModelMapper modelMapper;

  @Autowired
  public PropertyService(PropertyRepository propertyRepository, ModelMapper modelMapper) {
    this.propertyRepository = propertyRepository;
    this.modelMapper = modelMapper;
  }

  public List<PropertyDto> findAll() {
    List<PropertyDto> result = new ArrayList<>();

    propertyRepository.findAll().forEach(property -> {
      result.add(modelMapper.map(property, PropertyDto.class));
    });

    return result;
  }

  public PropertyDto findById(long id) {
    Optional<Property> property = propertyRepository.findById(id);
    return property.map(property1 -> modelMapper.map(property1, PropertyDto.class)).orElse(null);
  }

  public Long create(PropertyDto propertyDto) {
    Property property = modelMapper.map(propertyDto, Property.class);
    return modelMapper.map(propertyRepository.save(property), PropertyDto.class).getId();
  }

  public int update(Long id, PropertyDto propertyDto) {
    if (findById(id) != null) {
      Property property = modelMapper.map(propertyDto, Property.class);
      property.setId(id);
      propertyRepository.save(property);
      return 1;
    } else {
      return 0;
    }
  }

  public int deleteById(long id) {
    if (findById(id) != null) {
      propertyRepository.deleteById(id);
      return 1;
    } else {
      return 0;
    }
  }
}
