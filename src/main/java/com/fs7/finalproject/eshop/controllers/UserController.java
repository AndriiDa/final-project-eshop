package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/current")
  public ResponseEntity<User> getCurrentUser() {
    User user = new User();
    user.setFirstName("Petya");
    user.setLastName("Ivanov");
    user.setLoginName("p.ivanov");
    user.setEmail("p.ivanov@gmail.com");
    user.setGender(Gender.MALE);
    userRepository.save(user);
    User userFind = userRepository.getOne(user.getId());
    log.info("Serving user");

    return new ResponseEntity(userFind, HttpStatus.ACCEPTED);
  }
}
