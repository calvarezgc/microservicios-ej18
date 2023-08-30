package com.example.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cartservice.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

