package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    List<User> result = new ArrayList<>();
    userRepository.findAll().forEach(user -> {
      //UserDto userDto = modelMapper.map(user, UserDto.class);
      result.add(user);
    });
    return result;
  }

  public Optional<User> findById(long id) {
    return userRepository.findById(id);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void deleteById(long id) {
    userRepository.deleteById(id);
  }
}
