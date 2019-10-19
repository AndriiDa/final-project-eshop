package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> findById(long id) {
    return userRepository.findById(id);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteById(long id) {
    userRepository.deleteById(id);
  }
}
