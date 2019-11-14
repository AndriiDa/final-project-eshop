package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
  List<Brand> findAll();

  Optional<Brand> findById(Long id);

  Brand save(Brand brand);

  void deleteById(Long id);
}
