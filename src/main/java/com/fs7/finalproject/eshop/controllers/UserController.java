package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Address;
import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // GET method to fetch user by Id
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getPhoneById(@PathVariable(value = "id") Long userId)
          throws Exception {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User " + userId + " not found"));
    return ResponseEntity.ok().body(user);
  }

  // POST method to create a user
  @PostMapping("/users")
  public User createPhone(@Valid @RequestBody User user) {
    return userRepository.save(user);
  }

  // PUT method to update a user's details
  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(
          @PathVariable(value="id") Long userId, @Valid @RequestBody User userDetails
  ) throws Exception {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("Phone " + userId + " not found"));

    user.setFirstName(userDetails.getFirstName());
    user.setLastName(userDetails.getLastName());

    final User updatedUser = userRepository.save(user);
    return ResponseEntity.ok(updatedUser);
  }

  // DELETE method to delete a user
  @DeleteMapping("/user/{id}")
  public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long userId) throws Exception {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User " + userId + " not found"));

    userRepository.delete(user);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }

  @GetMapping("/users/current")
  public ResponseEntity<User> getCurrentUser() {
    Address address = Address.builder()
            .addressLine("address text")
            .build();
    User user = User.builder()
            .firstName("Petys")
            .lastName("Ivanov")
            .loginName("pivanov")
            .email("p_ivanov@gmail.com")
            .gender(Gender.MALE)
            .role(Role.ADMIN)
            .address(address)
            .build();
    userRepository.save(user);
    User userFind = userRepository.getOne(user.getId());
    log.info("Serving user");

    return new ResponseEntity(userFind, HttpStatus.ACCEPTED);
  }
}
