package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
  List<User> findAll();

  Optional<User> findById(long id);

  User save(User user);

  void deleteById(long id);
}
