package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Vendor;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.model.dto.VendorDto;
import com.fs7.finalproject.eshop.model.mapper.ProductMapper;
import com.fs7.finalproject.eshop.model.mapper.VendorMapper;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.VendorRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
  private VendorRepository vendorRepository;
  private UserRepository userRepository;
  private ProductRepository productRepository;
  private VendorMapper vendorMapper;
  private ProductMapper productMapper;

  @Autowired
  public VendorService(VendorRepository vendorRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository,
                       VendorMapper vendorMapper,
                       ProductMapper productMapper) {
    this.vendorRepository = vendorRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.vendorMapper = vendorMapper;
    this.productMapper = productMapper;

  }

  public Page<VendorDto> findAll(Pageable pageable) {
    return vendorRepository.findAll(pageable).map(item -> (vendorMapper.toDto(item)));
  }

  public Page<VendorDto> findByNameAndActiveIsTrue(String paramNameValue, Boolean paramIsActiveValue, Pageable pageable) {

    Vendor vendor = new Vendor();
    vendor.setName(paramNameValue);
    vendor.setIsActive(paramIsActiveValue);

    ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnoreCase("name");
    Example<Vendor> example = Example.of(vendor, matcher);

    return vendorRepository.findAll(example, pageable).map(item -> (vendorMapper.toDto(item)));
  }

  public VendorDto update(Long id, VendorDto source) {
    Vendor toUpdate = vendorMapper.toEntity(source);
    return vendorRepository.findById(id)
        .map(item -> {
          item.setName(toUpdate.getName());
          item.setNotes(toUpdate.getNotes());
          item.setIsActive(toUpdate.getIsActive());
          Long crUserId = source.getCrUserId();
          Long lmUserId = source.getLmUserId();
          User crUser = userRepository.findById(crUserId)
              .orElseThrow(() -> new ResourceNotFoundException("crUserId " + crUserId
                  + ", specified in the request body json, - not found"));
          User lmUser = userRepository.findById(lmUserId)
              .orElseThrow(() -> new ResourceNotFoundException("lmUserId " + lmUserId
                  + ", specified in the request body json, - not found"));
          item.setCrUser(crUser);
          item.setLmUser(lmUser);
          item.setId(id);
          return vendorMapper.toDto(vendorRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("VendorId " + id + " not found"));
  }

  public ResponseEntity<?> deleteById(Long id) {
    return vendorRepository.findById(id).map(item -> {
      vendorRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("VendorId " + id + " not found"));
  }

  public VendorDto save(VendorDto source) {
    return vendorMapper.toDto(vendorRepository.save(vendorMapper.toEntity(source)));
  }

  public Object findById(Long id) {
    return vendorRepository.findById(id)
        .map(item -> vendorMapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("VendorId " + id + " not found"));
  }

  public Page<ProductDto> findAllProductsByVendorId(Long id, Pageable pageable) {
    return productRepository.findAllByVendor(vendorMapper.toEntity((VendorDto) findById(id)), pageable)
        .map(item -> productMapper.toDto(item));
  }
}
