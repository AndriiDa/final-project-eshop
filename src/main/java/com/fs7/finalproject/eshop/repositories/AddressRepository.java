package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository  extends JpaRepository<Address, Long> {
}
