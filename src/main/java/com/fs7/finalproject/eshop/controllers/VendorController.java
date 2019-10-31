package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.services.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/vendors")
public class VendorController {
  private VendorServiceImpl vendorService;

  @Autowired
  public VendorController(VendorServiceImpl vendorService) {
    this.vendorService = vendorService;
  }

  @GetMapping
  public ResponseEntity<List<Vendor>> getAllVendors() {
    return ResponseEntity.ok(vendorService.findAll());
  }

  @PostMapping
  public void postAllVendors() {
  }

  @PutMapping({"vendorId"})
  public void putAllVendors() {
  }


  @DeleteMapping("{vendorId}")
  public void deleteAllVendors() {
  }

}
