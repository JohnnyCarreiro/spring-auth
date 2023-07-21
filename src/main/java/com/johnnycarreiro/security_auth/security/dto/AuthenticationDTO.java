package com.johnnycarreiro.security_auth.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(@NotBlank String username, @NotBlank String password) {

}
