package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {
  Optional<PropertyValue> findByName(String name);
}
