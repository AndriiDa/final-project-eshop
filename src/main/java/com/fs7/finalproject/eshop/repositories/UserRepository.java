package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
  //  List<User> getUsersByRoleEquals(Role role);
  //  Optional<User> getUserByLoginName(String loginName);
}
