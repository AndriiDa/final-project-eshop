package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.BrandDto;
import com.fs7.finalproject.eshop.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
  private BrandService brandService;

  @Autowired
  public BrandController(BrandService brandService) {
    this.brandService = brandService;
  }

  @GetMapping
  public ResponseEntity<List<BrandDto>> findAll() {
    return ResponseEntity.ok(brandService.findAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<BrandDto> findById(@PathVariable("id") Long id) {
    BrandDto brandDto = brandService.findById(id);
    if (brandDto == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(brandDto);
    }
  }

  @PostMapping
  public ResponseEntity<Long> create(@RequestBody BrandDto itemDto) {
    return ResponseEntity.ok(brandService.create(itemDto));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int update(@PathVariable("id") Long id, @RequestBody BrandDto itemDto) {
    return brandService.update(id, itemDto);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int delete(@PathVariable("id") Long id) {
    return brandService.deleteById(id);
  }
}

