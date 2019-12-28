package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.dto.UserDto;
import com.fs7.finalproject.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Page<UserDto>> findAll(@RequestParam(required = false) Map<String, String> allParams,
                                               Pageable pageable) {
    return allParams.isEmpty()
        ? ResponseEntity.ok(userService.findAll(pageable))
        : ResponseEntity.ok(userService.findByParams(allParams, pageable));
  }

  @PostMapping
  public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto source) {
    return ResponseEntity.ok(userService.save(source));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> findById(@Valid @PathVariable Long id) {
    return Objects.nonNull(userService.findById(id))
        ? ResponseEntity.ok(userService.findById(id))
        : ResponseEntity.notFound().build();
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<UserDto> update(@Valid @PathVariable("id") Long id, @Valid @RequestBody UserDto userDto) {
    return ResponseEntity.ok(userService.update(id, userDto));
  }

  @PutMapping("/{id}/inactivate")
  public ResponseEntity<UserDto> setInactive(@Valid @PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.setInactive(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable Long id) {
    return ResponseEntity.ok(userService.deleteById(id));
  }
  
  //@PostMapping("/sign-up")
  //public ResponseEntity<Object> signUp(@Valid @RequestBody UserDto user) {
  //  return ResponseEntity.ok(userService.signUp(user));
  //}
}
