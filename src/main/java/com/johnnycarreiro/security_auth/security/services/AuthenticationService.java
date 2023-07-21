package com.johnnycarreiro.security_auth.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.johnnycarreiro.security_auth.modules.user.RoleRepository;
import com.johnnycarreiro.security_auth.modules.user.UserRepository;
import com.johnnycarreiro.security_auth.modules.user.entities.Role;
import com.johnnycarreiro.security_auth.modules.user.entities.User;
import com.johnnycarreiro.security_auth.security.dto.UserRegisterRequest;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  public void register(UserRegisterRequest userRegisterRequest) throws Exception {
    User userExists = this.userRepository.findByUsername(userRegisterRequest.username());

    if (userExists != null) {
      throw new Error("User Already exists: " + userRegisterRequest.username());
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterRequest.password());
    List<Role> roles = new ArrayList<>();
    if (this.userRepository.count() <= 0) {
      Role role = this.roleRepository.findByName("ADMIN");
      roles.add(role);
      User user = new User(userRegisterRequest.name(), userRegisterRequest.username(), encryptedPassword, roles);
      this.userRepository.save(user);
      return;
    }
    Role role = this.roleRepository.findByName("USER");
    roles.add(role);
    User user = new User(userRegisterRequest.name(), userRegisterRequest.username(), encryptedPassword, roles);
    this.userRepository.save(user);
    return;
  }

}
