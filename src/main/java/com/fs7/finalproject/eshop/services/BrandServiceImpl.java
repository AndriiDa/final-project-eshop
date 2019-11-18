package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Brand;
import com.fs7.finalproject.eshop.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

  private BrandRepository brandRepository;

  @Autowired
  public BrandServiceImpl(BrandRepository brandRepository) {
    this.brandRepository = brandRepository;
  }

  @Override
  public List<Brand> findAll() {
    return brandRepository.findAll();
  }

  @Override
  public Optional<Brand> findById(Long id) {
    return brandRepository.findById(id);
  }

  @Override
  public Brand save(Brand brand) {
    return brandRepository.save(brand);
  }

  @Override
  public void deleteById(Long id) {
    brandRepository.deleteById(id);
  }
}
