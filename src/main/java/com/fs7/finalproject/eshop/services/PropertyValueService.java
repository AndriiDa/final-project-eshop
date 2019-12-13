package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.PropertyValue;
import com.fs7.finalproject.eshop.model.dto.PropertyValueDto;
import com.fs7.finalproject.eshop.model.mapper.PropertyValueMapper;
import com.fs7.finalproject.eshop.repositories.PropertyRepository;
import com.fs7.finalproject.eshop.repositories.PropertyValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyValueService {
  private PropertyValueRepository propertyValueRepository;
  private PropertyRepository propertyRepository;
  private PropertyValueMapper mapper;

  @Autowired
  public PropertyValueService(PropertyValueRepository propertyValueRepository,
                              PropertyRepository propertyRepository,
                              PropertyValueMapper mapper) {
    this.propertyValueRepository = propertyValueRepository;
    this.propertyRepository = propertyRepository;
    this.mapper = mapper;
  }

  public Page<PropertyValueDto> findByPropertyId(Long id, Pageable pageable) {
    return propertyValueRepository.findByPropertyId(id, pageable)
        .map(item -> (mapper.toDto(item)));
  }

  public PropertyValueDto createPropertyValue(Long propertyId, PropertyValueDto propertyValueDto) {
    PropertyValue propertyValue = mapper.toEntity(propertyValueDto);
    return propertyRepository.findById(propertyId).map(property -> {
      propertyValue.setProperty(property);
      return mapper.toDto(propertyValueRepository.save(propertyValue));
    }).orElseThrow(() -> new ResourceNotFoundException("propertyId " + propertyId + " not found"));
  }

  public PropertyValueDto updatePropertyValue(Long propertyId,
                                              Long propertyValueId,
                                              PropertyValueDto propertyValueDto) {
    if (!propertyRepository.existsById(propertyId)) {
      throw new ResourceNotFoundException("propertyId " + propertyId + " not found");
    }

    return propertyValueRepository.findById(propertyValueId).map(propertyValue -> {
      propertyValue.setName(mapper.toEntity(propertyValueDto).getName());
      propertyValue.setDescription(mapper.toEntity(propertyValueDto).getDescription());
      propertyValue.setProperty(mapper.toEntity(propertyValueDto).getProperty());
      return mapper.toDto(propertyValueRepository.save(propertyValue));
    }).orElseThrow(() -> new ResourceNotFoundException("propertyValueId " + propertyValueId + "not found"));
  }

  public ResponseEntity<?> deletePropertyValue(Long propertyId,
                                               Long propertyValueId) {
    return propertyValueRepository.findByIdAndPropertyId(propertyValueId, propertyId).map(propertyValue -> {
      propertyValueRepository.delete(propertyValue);
      return ResponseEntity.ok().build();
    }).orElseThrow(() ->
        new ResourceNotFoundException("propertyValue not found with id "
            + propertyValueId + " and propertyId " + propertyId));
  }
}
