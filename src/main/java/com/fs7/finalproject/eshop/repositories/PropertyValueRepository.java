package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.PropertyValue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyValueRepository extends CrudRepository<PropertyValue, Long> {
  Optional<PropertyValue> findByName(String name);
}
