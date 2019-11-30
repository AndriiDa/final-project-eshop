package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  public List<ProductDto> findAll() {
    List<ProductDto> result = new ArrayList<>();

    productRepository.findAll().forEach(product -> {
      result.add(modelMapper.map(product, ProductDto.class));
    });

    return result;
  }

  public ProductDto findById(long id) {
    Optional<Product> category = productRepository.findById(id);
    return category.map(product1 -> modelMapper.map(product1, ProductDto.class)).orElse(null);
  }

  public Long create(ProductDto productDto) {
    Product product = modelMapper.map(productDto, Product.class);
    return modelMapper.map(productRepository.save(product), ProductDto.class).getId();
  }

  public int update(Long id, ProductDto productDto) {
    if (findById(id) != null) {
      Product product = modelMapper.map(productDto, Product.class);
      Long crUserId = productDto.getCrUserId();
      Long lmUserId = productDto.getLmUserId();
      User crUser = userRepository.findById(crUserId).orElse(null);
      User lmUser = userRepository.findById(lmUserId).orElse(null);
      product.setCrUser(crUser);
      product.setLmUser(lmUser);
      product.setId(id);
      productRepository.save(product);
      return 1;
    } else {
      return 0;
    }
  }

  public int deleteById(long id) {
    if (findById(id) != null) {
      productRepository.deleteById(id);
      return 1;
    } else {
      return 0;
    }
  }
}

