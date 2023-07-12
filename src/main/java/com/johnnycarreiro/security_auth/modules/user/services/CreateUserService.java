package com.johnnycarreiro.security_auth.modules.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnnycarreiro.security_auth.modules.user.UserRepository;
import com.johnnycarreiro.security_auth.modules.user.entities.User;

@Service
public class CreateUserService {

  @Autowired
  UserRepository userRepository;

  public User execute(User user) {
    User userExists = this.userRepository.findByUsername(user.getUsername());

    if (userExists != null) {
      throw new Error("User Already exists: " + user.getUsername());
    }

    User createdUser = this.userRepository.save(user);

    return createdUser;
  }

}
