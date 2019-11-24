package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.services.VendorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {
  private VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping
  public ResponseEntity<List<Vendor>> findAll() {
    return ResponseEntity.ok(vendorService.findAll());
  }
}
