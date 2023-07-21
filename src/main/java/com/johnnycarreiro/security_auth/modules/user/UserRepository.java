package com.johnnycarreiro.security_auth.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.johnnycarreiro.security_auth.modules.user.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String username);

  @Query("SELECT u FROM  User u JOIN FETCH u.roles WHERE u.username = :username")
  User findByUsernameFetchRoles(@Param("username") String username);

}
