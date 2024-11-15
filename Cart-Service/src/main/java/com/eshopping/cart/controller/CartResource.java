package com.eshopping.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.cart.dto.CartDTO;
import com.eshopping.cart.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carts")
public class CartResource {

	private final CartService cartService;

	@Autowired
	public CartResource(CartService cartService) {
		this.cartService = cartService;
	}

	@Operation(summary = "Get a cart by its ID")
	@GetMapping("/{cartId}")
	public ResponseEntity<CartDTO> getCart(@PathVariable int cartId) {
		CartDTO cartDTO = cartService.getCartById(cartId);
		return new ResponseEntity<>(cartDTO, HttpStatus.OK);
	}

	@Operation(summary = "Get all carts")
	@GetMapping
	public List<CartDTO> getAllCarts() {
		return cartService.getAllCarts();
	}

	@Operation(summary = "Add a new cart")
	@PostMapping
	public ResponseEntity<CartDTO> addCart(@Valid @RequestBody CartDTO cartDTO) {
		CartDTO savedCartDTO = cartService.addCart(cartDTO);
		return new ResponseEntity<>(savedCartDTO, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a cart by its ID")
	@PutMapping("/{cartId}")
	public ResponseEntity<CartDTO> updateCart(@PathVariable int cartId, @Valid @RequestBody CartDTO cartDTO) {
		cartDTO.setCartId(cartId);
		CartDTO updatedCartDTO = cartService.updateCart(cartDTO);
		return new ResponseEntity<>(updatedCartDTO, HttpStatus.OK);
	}

	@Operation(summary = "Get the total of a cart by its ID")
	@GetMapping("/{cartId}/total")
	public ResponseEntity<Double> getCartTotal(@PathVariable int cartId) {
		double total = cartService.getCartTotal(cartId);
		return new ResponseEntity<>(total, HttpStatus.OK);
	}

}
