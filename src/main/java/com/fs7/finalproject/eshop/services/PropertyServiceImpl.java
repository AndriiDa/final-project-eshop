package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.Property;
import com.fs7.finalproject.eshop.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("propertyService")
@Transactional
public class PropertyServiceImpl implements PropertyService {
  @Autowired
  private PropertyRepository propertyRepository;

  @Override
  public List<Property> findAll() {
    return propertyRepository.findAll();
  }

  @Override
  public Optional<Property> findById(long id) {
    return propertyRepository.findById(id);
  }

  @Override
  public Property save(Property property) {
    return propertyRepository.save(property);
  }

  @Override
  public void deleteById(long id) {
    propertyRepository.deleteById(id);
  }
}
