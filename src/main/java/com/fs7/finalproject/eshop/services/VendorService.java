package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.Vendor;
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
import org.springframework.util.SerializationUtils;

import java.util.Map;

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

  public Page<VendorDto> findByParams(Map<String, String> allParams, Pageable pageable) {
    // {name}{isactive}
    Vendor template = new Vendor();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "name":
          template.setName(String.valueOf(allParams.get(item)));
          break;
        case "isactive":
          template.setIsActive(Boolean.valueOf(allParams.get(item)));
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<Vendor> example = Example.of(template, matcher);

    return vendorRepository.findAll(example, pageable).map(item -> (vendorMapper.toDto(item)));
  }

  public VendorDto update(Long id, VendorDto source) {
    return vendorRepository.findById(id)
        .map(item -> {
          Vendor destination = (Vendor) SerializationUtils
              .deserialize(SerializationUtils.serialize(vendorMapper.toEntity(source)).clone());
          destination.setCrUser(
              userRepository.findById(source.getCrUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "crUserId", source.getCrUserId()))
          );
          destination.setLmUser(
              userRepository.findById(source.getLmUserId())
                  .orElseThrow(() -> new ResourceNotFoundException("User", "lmUserId", source.getLmUserId()))
          );
          destination.setId(id);
          return vendorMapper.toDto(vendorRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("Vendor", "VendorId", id));
  }

  public VendorDto save(VendorDto source) {
    return vendorMapper.toDto(vendorRepository.save(vendorMapper.toEntity(source)));
  }

  public VendorDto findById(Long id) {
    return vendorRepository.findById(id)
        .map(item -> vendorMapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("Vendor", "VendorId", id));
  }

  public Page<ProductDto> findAllProductsByVendorId(Long id, Pageable pageable) {
    return productRepository.findAllByVendor(vendorMapper.toEntity((VendorDto) findById(id)), pageable)
        .map(item -> productMapper.toDto(item));
  }

  public VendorDto setInactive(Long id) {
    return vendorRepository.findById(id)
        .map(item -> {
          item.setIsActive(false);
          return vendorMapper.toDto(vendorRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("Vendor", "VendorId", id));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return vendorRepository.findById(id).map(item -> {
      vendorRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("Vendor", "VendorId", id));
  }

}
