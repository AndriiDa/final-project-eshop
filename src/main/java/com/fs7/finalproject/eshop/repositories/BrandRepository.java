package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
