package com.johnnycarreiro.security_auth.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.johnnycarreiro.security_auth.modules.user.UserRepository;
import com.johnnycarreiro.security_auth.modules.user.entities.User;
import com.johnnycarreiro.security_auth.security.UserPrincipal;

@Service
public class AuthorizationService implements UserDetailsService {
  private UserRepository userRepository;

  public AuthorizationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User userExists = this.userRepository.findByUsernameFetchRoles(username);

    if (userExists == null)
      throw new Error("User " + username + " does not exist");

    return UserPrincipal.create(userExists);
  }

}
