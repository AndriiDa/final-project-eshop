package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
  Optional<Property> findByName(String name);
}
