package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
  List<Vendor> findAll();

  Optional<Vendor> findById(Long id);

  Vendor save(Vendor brand);

  void deleteById(Long id);
}
