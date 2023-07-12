package com.johnnycarreiro.security_auth.modules.product.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnnycarreiro.security_auth.modules.product.ProductRepository;
import com.johnnycarreiro.security_auth.modules.product.entities.Product;

@Service
public class ListProductService {

  @Autowired
  ProductRepository productRepository;

  public List<Product> listAll() {
    return this.productRepository.findAll();
  }
}
