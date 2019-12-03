package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.PropertyValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {

  Optional<PropertyValue> findByName(String name);

  Page<PropertyValue> findByPropertyId(Long propertyId, Pageable pageable);

  Optional<PropertyValue> findByIdAndPropertyId(Long id, Long propertyId);

}
