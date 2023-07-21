package com.johnnycarreiro.security_auth.security.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnnycarreiro.security_auth.security.UserPrincipal;
import com.johnnycarreiro.security_auth.security.dto.AuthenticationDTO;
import com.johnnycarreiro.security_auth.security.dto.LoginResponse;
import com.johnnycarreiro.security_auth.security.dto.UserRegisterRequest;
import com.johnnycarreiro.security_auth.security.services.AuthenticationService;
import com.johnnycarreiro.security_auth.security.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final AuthenticationService authService;
  private final TokenService tokenService;

  public AuthController(
      AuthenticationManager authenticationManager,
      AuthenticationService authService,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.authService = authService;
    this.tokenService = tokenService;
  }

  @PostMapping("login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(
        authenticationDTO.username(), authenticationDTO.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    var token = this.tokenService.generateToken((UserPrincipal) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponse(token));
  }

  @PostMapping("register")
  public ResponseEntity<String> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
    try {
      this.authService.register(userRegisterRequest);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      System.out.println(">>>>>>>>>>>> User already registered");
      return ResponseEntity.badRequest().build();
    }
  }

}
