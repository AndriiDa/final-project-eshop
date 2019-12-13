package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
  private PropertyService propertyService;

  @Autowired
  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @GetMapping
  public ResponseEntity<?> findAll(@RequestParam(required = false) Map<String, String> allParams, Pageable pageable) {
    String param = "name";
    return allParams.containsKey(param)
        ? ResponseEntity.ok(propertyService.findByName(String.valueOf(allParams.get(param))))
        : ResponseEntity.ok(propertyService.findAll(pageable));
  }

  @PostMapping
  public PropertyDto create(@Valid @RequestBody PropertyDto item) {
    return propertyService.save(item);
  }

  @PutMapping("/{id}")
  public PropertyDto update(@PathVariable Long id, @Valid @RequestBody PropertyDto item) {
    return propertyService.update(id, item);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    return propertyService.deleteById(id);
  }

  @GetMapping(path = "/{id}")
  public PropertyDto findById(@PathVariable Long id) {
    return propertyService.findById(id);
  }
}
