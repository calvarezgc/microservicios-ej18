package com.example.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cartservice.entity.CartProduct;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
}

