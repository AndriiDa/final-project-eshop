package com.fs7.finalproject.eshop.repositories;

import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  List<User> getUsersByRoleEquals(Role role);

  Optional<User> findUserByLoginName(String loginName);

  Optional<User> findUserByLoginNameOrEmail(String loginName, String email);

  List<User> findByIdIn(List<Long> userIds);

  Optional<User> findByLoginName(String username);

  Boolean existsByLoginName(String username);

  Boolean existsByEmail(String email);

}
