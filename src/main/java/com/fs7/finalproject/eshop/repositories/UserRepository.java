package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
  //  List<User> getUsersByRoleEquals(Role role);
  //  Optional<User> getUserByLoginName(String loginName);
}
