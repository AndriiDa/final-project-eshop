package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Product;
import com.fs7.finalproject.eshop.model.ProductPropertyValue;
import com.fs7.finalproject.eshop.model.PropertyValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValue, Long> {

  Page<ProductPropertyValue> findAllByProduct(Product target, Pageable pageable);

  Page<ProductPropertyValue> findAllByPropertyValue(PropertyValue target, Pageable pageable);

}
