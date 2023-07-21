package com.johnnycarreiro.security_auth.security.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.johnnycarreiro.security_auth.security.UserPrincipal;

@Service
public class TokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(UserPrincipal user) {
    try {
      Algorithm augorithm = Algorithm.HMAC256(secret);
      String token = JWT.create()
          .withIssuer("Jonny-Carreiro_authsys")
          .withSubject(user.getUsername())
          .withExpiresAt(this.generateExpirationDate())
          .sign(augorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException("Error while generate Token" + e.getMessage());
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm augorithm = Algorithm.HMAC256(secret);
      return JWT.require(augorithm)
          .withIssuer("Jonny-Carreiro_authsys")
          .build()
          .verify(token)
          .getSubject();

    } catch (JWTVerificationException e) {
      // throw new RuntimeException("Error while verifing Token" + e.getMessage());
      return "";
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
