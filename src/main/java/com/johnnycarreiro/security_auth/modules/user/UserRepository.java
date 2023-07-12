package com.johnnycarreiro.security_auth.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnnycarreiro.security_auth.modules.user.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String username);

}
