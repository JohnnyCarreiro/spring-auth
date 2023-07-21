package com.johnnycarreiro.security_auth.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.johnnycarreiro.security_auth.modules.user.entities.User;

import lombok.Data;

@Data
public class UserPrincipal implements UserDetails {
  private final String username;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;

  private UserPrincipal(User user) {
    this.username = user.getUsername();
    this.password = user.getPassword();
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    authorities = user.getRoles().stream().map(role -> {
      return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
    }).collect(Collectors.toList());

    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {
    return new UserPrincipal(user);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
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
}
