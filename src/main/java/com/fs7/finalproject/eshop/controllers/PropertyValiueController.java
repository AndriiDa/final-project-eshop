package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.PropertyValueDto;
import com.fs7.finalproject.eshop.services.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyValiueController {
  private PropertyValueService propertyValueService;

  @Autowired
  public PropertyValiueController(PropertyValueService propertyValueService) {
    this.propertyValueService = propertyValueService;
  }

  @GetMapping("/{propertyId}/propertyvalues")
  public ResponseEntity<Page<PropertyValueDto>> findByPropertyId(@Valid @PathVariable(value = "propertyId") Long propertyId,
                                                                 Pageable pageable) {
    return ResponseEntity.ok(propertyValueService.findByPropertyId(propertyId, pageable));
  }

  @PostMapping("/{propertyId}/propertyvalues")
  public PropertyValueDto create(@Valid @PathVariable(value = "propertyId") Long propertyId,
                                              @Valid @RequestBody PropertyValueDto propertyValueDto) {
    return propertyValueService.save(propertyId, propertyValueDto);
  }

  @PutMapping("/{propertyId}/propertyvalues/{propertyValueId}")
  public ResponseEntity<PropertyValueDto> save(@PathVariable(value = "propertyId") Long propertyId,
                                              @PathVariable(value = "propertyValueId") Long propertyValueId,
                                              @Valid @RequestBody PropertyValueDto source) {

    return ResponseEntity.ok(propertyValueService.update(propertyId, propertyValueId, source));
  }

  @DeleteMapping("/{propertyId}/propertyvalues/{propertyValueId}")
  public ResponseEntity<Object> deletePropertyValue(@PathVariable(value = "propertyId") Long propertyId,
                                               @PathVariable(value = "propertyValueId") Long propertyValueId) {
    return ResponseEntity.ok(propertyValueService.deletePropertyValue(propertyId, propertyValueId));
  }
}