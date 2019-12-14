package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Page<Product> findAllByVendor(Vendor vendor, Pageable pageable);
}
