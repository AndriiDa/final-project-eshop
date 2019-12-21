package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.services.PropertyService;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
  private PropertyService propertyService;

  @Autowired
  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @GetMapping
  public ResponseEntity<Page<PropertyDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                                   Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(propertyService.findAll(pageable))
        : ResponseEntity.ok(propertyService.findAllByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<PropertyDto> create(@Valid @RequestBody PropertyDto source) {
    return ResponseEntity.ok(propertyService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PropertyDto> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(propertyService.findById(id))
        ? ResponseEntity.ok(propertyService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<PropertyDto> update(@Valid @PathVariable Long id, @Valid @RequestBody PropertyDto source) {
    return ResponseEntity.ok(propertyService.update(id, source));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(propertyService.deleteById(id));
  }

}
