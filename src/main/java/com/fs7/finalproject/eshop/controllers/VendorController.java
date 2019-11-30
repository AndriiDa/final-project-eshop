package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.VendorDto;
import com.fs7.finalproject.eshop.services.VendorService;
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
@RequestMapping("/api/v1/vendors")
public class VendorController {
  private VendorService vendorService;

  @Autowired
  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping
  public ResponseEntity<List<VendorDto>> findAll() {
    return ResponseEntity.ok(vendorService.findAll());
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<VendorDto> findById(@PathVariable("id") Long id) {
    VendorDto vendorDto = vendorService.findById(id);
    if (vendorDto == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(vendorDto);
    }
  }

  @PostMapping
  public ResponseEntity<Long> create(@RequestBody VendorDto vendorDto) {
    return ResponseEntity.ok(vendorService.create(vendorDto));
  }

  @PutMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int update(@PathVariable("id") Long id, @RequestBody VendorDto vendorDto) {
    return vendorService.update(id, vendorDto);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public int delete(@PathVariable("id") Long id) {
    return vendorService.deleteById(id);
  }
}
