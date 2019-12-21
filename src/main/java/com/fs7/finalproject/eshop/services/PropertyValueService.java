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
import org.springframework.util.SerializationUtils;

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

  public PropertyValueDto save(Long propertyId, PropertyValueDto source) {
    return propertyRepository.findById(propertyId).map(item -> {
      PropertyValue destination = mapper.toEntity(source);
      destination.setProperty(item);
      return mapper.toDto(propertyValueRepository.save(destination));
    }).orElseThrow(() -> new ResourceNotFoundException("PropertyId " + propertyId + " not found"));
  }

  public PropertyValueDto update(Long propertyId,
                                              Long propertyValueId,
                                              PropertyValueDto source) {
    if (!propertyRepository.existsById(propertyId)) {
      throw new ResourceNotFoundException("PropertyId " + propertyId + " not found");
    }

    return propertyValueRepository.findById(propertyValueId).map(item -> {
      PropertyValue destination = (PropertyValue) SerializationUtils
          .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
      destination.setId(propertyValueId);

      return mapper.toDto(propertyValueRepository.save(destination));
    }).orElseThrow(() -> new ResourceNotFoundException("PropertyValueId " + propertyValueId + "not found"));
  }

  public ResponseEntity<Object> deletePropertyValue(Long propertyId,
                                               Long propertyValueId) {
    return propertyValueRepository.findByIdAndPropertyId(propertyValueId, propertyId).map(propertyValue -> {
      propertyValueRepository.delete(propertyValue);
      return ResponseEntity.ok().build();
    }).orElseThrow(() ->
        new ResourceNotFoundException("PropertyValue not found with id "
            + propertyValueId + " and propertyId " + propertyId));
  }
}
