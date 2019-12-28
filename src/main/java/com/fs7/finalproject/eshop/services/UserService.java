package com.fs7.finalproject.eshop.services;

import com.fs7.finalproject.eshop.exceptions.ResourceNotFoundException;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.model.dto.UserDto;
import com.fs7.finalproject.eshop.model.mapper.UserMapper;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;


import java.util.Map;

@Service
public class UserService {
  private UserRepository userRepository;
  private UserMapper mapper;
  //private BCryptPasswordEncoder bCryptPasswordEncoder;


  @Autowired
  public UserService(UserRepository userRepository,
                     UserMapper mapper
                     ) {
    this.userRepository = userRepository;
    this.mapper = mapper;
    //this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  public Page<UserDto> findAll(Pageable pageable) {
    return userRepository.findAll(pageable).map(item -> (mapper.toDto(item)));
  }

  public Page<UserDto> findByParams(Map<String, String> allParams, Pageable pageable) {
    // {login}{isactive}
    User template = new User();

    allParams.keySet().forEach(item -> {
      switch (item.toLowerCase()) {
        case "login":
          template.setLoginName(String.valueOf(allParams.get(item)));
          break;
        case "isactive":
          template.setIsActive(Boolean.valueOf(allParams.get(item)));
          break;
        default:
          break;
      }
    });

    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withIgnoreCase();

    Example<User> example = Example.of(template, matcher);

    return userRepository.findAll(example, pageable).map(item -> (mapper.toDto(item)));
  }

  public UserDto update(Long id, UserDto source) {
    return userRepository.findById(id)
        .map(item -> {
          User destination = (User) SerializationUtils
              .deserialize(SerializationUtils.serialize(mapper.toEntity(source)).clone());
          destination.setId(id);
          return mapper.toDto(userRepository.save(destination));
        }).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));
  }

  public UserDto save(UserDto source) {
    return mapper.toDto(userRepository.save(mapper.toEntity(source)));
  }

  public UserDto findById(Long id) {
    return userRepository.findById(id)
        .map(item -> mapper.toDto(item))
        .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));
  }

  public UserDto setInactive(Long id) {
    return userRepository.findById(id)
        .map(item -> {
          item.setIsActive(false);
          return mapper.toDto(userRepository.save(item));
        }).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));
  }

  public ResponseEntity<Object> deleteById(Long id) {
    return userRepository.findById(id).map(item -> {
      userRepository.delete(item);
      return ResponseEntity.ok().build();
    }).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", id));
  }

  //  public ResponseEntity<Object> signUp(UserDto source) {
  //    return userRepository.findById(source.getId())
  //        .map(destination -> {
  //          destination.setLoginPassword(bCryptPasswordEncoder.encode(source.getLoginPassword()));
  //          userRepository.save(destination);
  //        }).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", source.getId()));
  //  }
  //
  //  public UserDto findByLoginName(UserDto source) {
  //    return userRepository.findUserByLoginName(source.getLoginName())
  //        .map(item -> mapper.toDto(item))
  //        .orElseThrow(() -> new ResourceNotFoundException("User", "UserLoginName", source.getLoginName()));
  //  }
}
