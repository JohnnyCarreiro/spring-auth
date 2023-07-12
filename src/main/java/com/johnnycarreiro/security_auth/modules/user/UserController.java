package com.johnnycarreiro.security_auth.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnnycarreiro.security_auth.modules.user.dto.CreateUserRoleDTO;
import com.johnnycarreiro.security_auth.modules.user.entities.User;
import com.johnnycarreiro.security_auth.modules.user.services.CreateUserRoleService;
import com.johnnycarreiro.security_auth.modules.user.services.CreateUserService;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  CreateUserService createUserService;

  @Autowired
  CreateUserRoleService createUserRoleService;

  @PostMapping("/create")
  public User createUser(@RequestBody User user) {
    return this.createUserService.execute(user);
  }

  @PostMapping("/role")
  public User assingRoleToUser(@RequestBody CreateUserRoleDTO createUserRoleDTO) {
    return this.createUserRoleService.execute(createUserRoleDTO);
  }
}
