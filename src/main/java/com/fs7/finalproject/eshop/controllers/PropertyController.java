package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.PropertyDto;
import com.fs7.finalproject.eshop.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
  @Autowired
  private PropertyService propertyService;

  @GetMapping
  public ResponseEntity<List<PropertyDto>> findAll() {
    return ResponseEntity.ok(propertyService.findAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<PropertyDto> findById(@PathVariable("id") Long id) {
    PropertyDto productDto = propertyService.findById(id);
    if (productDto == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(productDto);
    }
  }

  @PostMapping
  public ResponseEntity<Long> create(@RequestBody PropertyDto productDto) {
    return ResponseEntity.ok(propertyService.create(productDto));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int update(@PathVariable("id") Long id, @RequestBody PropertyDto productDto) {
    return propertyService.update(id, productDto);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int delete(@PathVariable("id") Long id) {
    return propertyService.deleteById(id);
  }


}
