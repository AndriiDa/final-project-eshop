package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
