package com.johnnycarreiro.security_auth.modules.user.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateUserRoleDTO {
  private UUID idUser;
  private List<UUID> idsRoles;
}
