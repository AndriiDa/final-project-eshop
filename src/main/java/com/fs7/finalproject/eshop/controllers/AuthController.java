package com.fs7.finalproject.eshop.controllers;

import com.fs7.finalproject.eshop.model.Gender;
import com.fs7.finalproject.eshop.model.Role;
import com.fs7.finalproject.eshop.model.User;
import com.fs7.finalproject.eshop.payloads.ApiResponse;
import com.fs7.finalproject.eshop.payloads.JwtAuthenticationResponse;
import com.fs7.finalproject.eshop.payloads.LoginRequest;
import com.fs7.finalproject.eshop.payloads.SignUpRequest;
import com.fs7.finalproject.eshop.repositories.UserRepository;
import com.fs7.finalproject.eshop.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  JwtTokenProvider tokenProvider;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsernameOrEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
    if (userRepository.existsByLoginName(signUpRequest.getLoginName())) {
      return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
          HttpStatus.BAD_REQUEST);
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
          HttpStatus.BAD_REQUEST);
    }

    // Creating user's account
    User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(), false,
        signUpRequest.getLoginName(), signUpRequest.getLoginPassword());

    user.setLoginPassword(passwordEncoder.encode(user.getLoginPassword()));

    user.setRole(Role.USER);
    user.setIsActive(false);
    user.setGender(Gender.MALE);
    user.setCrTime(Date.valueOf(LocalDate.now()));
    user.setBirthDate(Date.valueOf(LocalDate.now().minusYears(50L)));

    User result = userRepository.save(user);

    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath().path("/users/{username}")
        .buildAndExpand(result.getLoginName()).toUri();

    return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
  }
}
