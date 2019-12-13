package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.model.PropertyValue;
import com.fs7.finalproject.eshop.model.dto.CategoryDto;
import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.model.mapper.PropertyMapper;
import com.fs7.finalproject.eshop.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  public PropertyDto save(PropertyDto item) {
    return mapper.toDto(
        propertyRepository.save(mapper.toEntity(item))
    );
  }

  public PropertyDto update(Long id, PropertyDto item) {
    Property itemToUpdate = mapper.toEntity(item);
    return propertyRepository.findById(id).map(item1 -> {
      item1.setName(itemToUpdate.getName());
      item1.setDescription(itemToUpdate.getDescription());
      item1.setPropertyValues(new ArrayList<>(itemToUpdate.getPropertyValues()));
      return mapper.toDto(propertyRepository.save(item1));
    }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + id + " not found"));
  }

  public ResponseEntity<?> deleteById(Long id) {
    return propertyRepository.findById(id).map(item -> {
      propertyRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + id + " not found"));
  }

  public PropertyDto findById(Long id) {
    return propertyRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("PropertyId " + id + " not found"));
  }

  public PropertyDto findByName(String name) {
    return propertyRepository.findPropertyByName(name)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("Property name \'" + name + "\' not found"));
  }

}
