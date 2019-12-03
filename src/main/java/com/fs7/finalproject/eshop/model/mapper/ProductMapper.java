package com.fs7.finalproject.eshop.model.mapper;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends AbstractMapper<Product, ProductDto> {

  @Autowired
  public ProductMapper() {
    super(Product.class, ProductDto.class);
  }
}
