package com.johnnycarreiro.security_auth.modules.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnnycarreiro.security_auth.modules.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
