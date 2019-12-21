package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.model.mapper.PropertyMapper;
import com.fs7.finalproject.eshop.repositories.PropertyRepository;
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
public class PropertyService {
  private PropertyRepository propertyRepository;
  private PropertyMapper mapper;

  @Autowired
  public PropertyService(PropertyRepository propertyRepository, PropertyMapper mapper) {
    this.propertyRepository = propertyRepository;
    this.mapper = mapper;
  }

  public Page<PropertyDto> findAll(Pageable pageable) {
    return propertyRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<PropertyDto> findAllByParams(Map<String, String> allParams, Pageable pageable) {
    // {name}
    Property template = new Property();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "name":
          template.setName(String.valueOf(allParams.get(item)));
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<Property> example = Example.of(template, matcher);

    return propertyRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public PropertyDto update(Long id, PropertyDto source) {
    return propertyRepository.findById(id).map(item1 -> {
      Property destination = (Property) SerializationUtils
          .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
      destination.setId(id);
      return mapper.toDto(propertyRepository.save(destination));
    }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + id + " not found"));
  }

  public PropertyDto save(PropertyDto item) {
    return mapper.toDto(
        propertyRepository.save(mapper.toEntity(item))
    );
  }

  public PropertyDto findById(Long id) {
    return propertyRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("PropertyId " + id + " not found"));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return propertyRepository.findById(id).map(item -> {
      propertyRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + id + " not found"));
  }
}
