package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
