package com.johnnycarreiro.security_auth.modules.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnnycarreiro.security_auth.modules.user.entities.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

  Role findByName(String name);

}
