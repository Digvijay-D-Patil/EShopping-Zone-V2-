package com.eshopping.cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	Optional<Cart> findByCartId(int cartId);
}