package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  public List<Product> findAll() {
    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    return products;
  }
}
