package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.services.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
  @Autowired
  private PropertyServiceImpl propertyService;

  @GetMapping
  public ResponseEntity<List<Property>> findAll() {
    return ResponseEntity.ok(propertyService.findAll());
  }
}
