package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.services.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
  private BrandService brandService;

  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  public ResponseEntity<List<Brand>> findAll(){
    return ResponseEntity.ok(brandService.findAll());
  }
}
