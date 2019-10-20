package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  private UserServiceImpl userService;

  @GetMapping
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

//  @GetMapping("/")
//  public List<User> getAllUsers() {
//    return userService.findAll();
//  }
//
//  // GET method to fetch user by Id
//  @GetMapping("/{id}")
//  public ResponseEntity<User> getPhoneById(@PathVariable(value = "id") Long userId)
//          throws Exception {
//    User user = userRepository.findById(userId)
//            .orElseThrow(() -> new Exception("User " + userId + " not found"));
//    return ResponseEntity.ok().body(user);
//  }
//
//  // POST method to create a user
//  @PostMapping("/")
//  public User createPhone(@Valid @RequestBody User user) {
//    return userRepository.save(user);
//  }
//
//  // PUT method to update a user's details
//  @PutMapping("/{id}")
//  public ResponseEntity<User> updateUser(
//          @PathVariable(value="id") Long userId, @Valid @RequestBody User userDetails
//  ) throws Exception {
//    User user = userRepository.findById(userId)
//            .orElseThrow(() -> new Exception("Phone " + userId + " not found"));
//
//    user.setFirstName(userDetails.getFirstName());
//    user.setLastName(userDetails.getLastName());
//
//    final User updatedUser = userRepository.save(user);
//    return ResponseEntity.ok(updatedUser);
//  }
//
//  // DELETE method to delete a user
//  @DeleteMapping("/{id}")
//  public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long userId) throws Exception {
//    User user = userRepository.findById(userId)
//            .orElseThrow(() -> new Exception("User " + userId + " not found"));
//
//    userRepository.delete(user);
//    Map<String, Boolean> response = new HashMap<>();
//    response.put("deleted", Boolean.TRUE);
//    return response;
//  }
}
