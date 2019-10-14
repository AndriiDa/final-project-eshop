package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
  List<User> getUsersByEmailEquals(String email);
//  Optional<User> getUserByName(String name);
}
