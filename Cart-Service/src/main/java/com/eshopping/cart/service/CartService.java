package com.eshopping.cart.service;

import java.util.List;

import com.eshopping.cart.dto.CartDTO;

public interface CartService {
	CartDTO getCartById(int cartId);

	List<CartDTO> getAllCarts();

	CartDTO addCart(CartDTO cart);

	CartDTO updateCart(CartDTO cart);

	double getCartTotal(int cartId);
}
