package com.johnnycarreiro.security_auth.modules.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnnycarreiro.security_auth.modules.user.UserRepository;
import com.johnnycarreiro.security_auth.modules.user.dto.CreateUserRoleDTO;
import com.johnnycarreiro.security_auth.modules.user.entities.Role;
import com.johnnycarreiro.security_auth.modules.user.entities.User;

@Service
public class CreateUserRoleService {

  @Autowired
  UserRepository userRepository;

  public User execute(CreateUserRoleDTO createUserRoleDTO) {
    Optional<User> userExists = this.userRepository.findById(createUserRoleDTO.getIdUser());
    List<Role> roles = new ArrayList<>();

    if (userExists.isEmpty()) {
      throw new Error("User does not exists");
    }

    roles = createUserRoleDTO.getIdsRoles().stream().map(role -> {
      return new Role(role);
    }).collect(Collectors.toList());

    User user = userExists.get();
    user.setRoles(roles);

    this.userRepository.save(user);

    return user;
  }
}
