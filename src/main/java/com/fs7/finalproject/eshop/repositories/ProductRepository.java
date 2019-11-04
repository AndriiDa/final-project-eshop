package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
