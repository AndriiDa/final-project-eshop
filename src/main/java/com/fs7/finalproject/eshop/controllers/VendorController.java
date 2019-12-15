package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.VendorDto;
import com.fs7.finalproject.eshop.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {
  private VendorService vendorService;

  @Autowired
  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping
  public ResponseEntity<?> findAll(@RequestParam(required = false) Map<String, String> allParams, Pageable pageable) {
    String paramName = "name";
    String paramIsActive = "isactive";
    String paramNameValue = null;
    Boolean paramIsActiveValue = null;

    if (allParams.keySet().contains(paramName)) {
      paramNameValue = String.valueOf(allParams.get(paramName));
    }

    if (allParams.keySet().contains(paramIsActive)) {
      paramIsActiveValue = Boolean.valueOf(allParams.get(paramIsActive));
    }

    return allParams.isEmpty()
        ? ResponseEntity.ok(vendorService.findAll(pageable))
        : ResponseEntity.ok(vendorService.findByNameAndActiveIsTrue(paramNameValue, paramIsActiveValue, pageable));
  }

  @PostMapping
  public ResponseEntity<?> create(@Valid @RequestBody VendorDto source) {
    return ResponseEntity.ok(vendorService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(vendorService.findById(id))
        ? ResponseEntity.ok(vendorService.findById(id))
        : ResponseEntity.notFound().build();
  }

  @GetMapping("/{id}/products")
  public ResponseEntity<?> findAllProductsByVendorId(@Valid @PathVariable Long id, Pageable pageable) {
    return ResponseEntity.ok(vendorService.findAllProductsByVendorId(id, pageable));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody VendorDto vendorDto) {
    return ResponseEntity.ok(vendorService.update(id, vendorDto));
  }

  @PutMapping("/{id}/inactivate")
  public ResponseEntity<?> setInactive(@Valid @PathVariable("id") Long id) {
    VendorDto vendor = (VendorDto) vendorService.findById(id);
    vendor.setIsActive(false);
    return ResponseEntity.ok(vendorService.save(vendor));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(vendorService.deleteById(id));
  }
}
