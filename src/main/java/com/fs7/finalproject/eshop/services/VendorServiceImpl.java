package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {

  private VendorRepository vendorRepository;

  @Autowired
  public VendorServiceImpl(VendorRepository vendorRepository) {
    this.vendorRepository = vendorRepository;
  }

  @Override
  public List<Vendor> findAll() {
    return vendorRepository.findAll();
  }

  @Override
  public Optional<Vendor> findById(Long id) {
    return vendorRepository.findById(id);
  }

  @Override
  public Vendor save(Vendor vendor) {
    return vendorRepository.save(vendor);
  }

  @Override
  public void deleteById(Long id) {
    vendorRepository.deleteById(id);
  }
}
