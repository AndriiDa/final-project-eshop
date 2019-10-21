package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
  List<Property> findAll();

  Optional<Property> findById(long id);

  Property save(Property property);

  void deleteById(long id);
}
