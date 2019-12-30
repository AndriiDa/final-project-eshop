package com.fs7.finalproject.eshop.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fs7.finalproject.eshop.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrincipal implements UserDetails {
  private Long id;

  private String firstName;

  private String lastName;

  private String loginName;

  @JsonIgnore
  private String email;

  @JsonIgnore
  private String loginPassword;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String firstName, String lastName,
                       String loginName, String loginPassword, String email,
                       Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.loginName = loginName;
    this.loginPassword = loginPassword;
    this.email = email;
    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities = Arrays.asList(
        new SimpleGrantedAuthority(user.getRole().name())
    );

    return new UserPrincipal(
        user.getId(),
        user.getFirstName(),
        user.getLastName(),
        user.getLoginName(),
        user.getLoginPassword(),
        user.getEmail(),
        authorities
    );
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getUsername() {
    return loginName;
  }

  @Override
  public String getPassword() {
    return loginPassword;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    UserPrincipal that = (UserPrincipal) obj;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
