package com.fs7.finalproject.eshop.security;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String loginNameOrEmail)
      throws UsernameNotFoundException {
    // Let people login with either username or email
    User user = userRepository.findUserByLoginNameOrEmail(loginNameOrEmail, loginNameOrEmail)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found with username or email : " + loginNameOrEmail)
        );

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", id)
    );

    return UserPrincipal.create(user);
  }
}