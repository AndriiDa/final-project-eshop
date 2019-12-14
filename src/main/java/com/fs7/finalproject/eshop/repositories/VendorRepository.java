package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

  Optional<Vendor> findByName(String name);

}
