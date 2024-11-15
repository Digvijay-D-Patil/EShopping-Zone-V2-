package com.eshopping.cart.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.eshopping.cart.dto.CartDTO;
import com.eshopping.cart.service.CartService;

class CartResourceTest {

	@InjectMocks
	private CartResource cartResource;

	@Mock
	private CartService cartService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getCart_ShouldReturnCart_WhenFound() {
		CartDTO cartDTO = new CartDTO();
		when(cartService.getCartById(1)).thenReturn(cartDTO);

		ResponseEntity<CartDTO> response = cartResource.getCart(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cartDTO, response.getBody());
		verify(cartService, times(1)).getCartById(1);
	}

	@Test
	void getAllCarts_ShouldReturnListOfCarts() {
		CartDTO cart1 = new CartDTO();
		CartDTO cart2 = new CartDTO();
		when(cartService.getAllCarts()).thenReturn(Arrays.asList(cart1, cart2));

		List<CartDTO> carts = cartResource.getAllCarts();

		assertEquals(2, carts.size());
		verify(cartService, times(1)).getAllCarts();
	}

	@Test
	void addCart_ShouldReturnCreatedCart() {
		CartDTO cartDTO = new CartDTO();
		when(cartService.addCart(any(CartDTO.class))).thenReturn(cartDTO);

		ResponseEntity<CartDTO> response = cartResource.addCart(cartDTO);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(cartDTO, response.getBody());
		verify(cartService, times(1)).addCart(cartDTO);
	}

	@Test
	void updateCart_ShouldReturnUpdatedCart() {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartId(1);
		when(cartService.updateCart(any(CartDTO.class))).thenReturn(cartDTO);

		ResponseEntity<CartDTO> response = cartResource.updateCart(1, cartDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cartDTO, response.getBody());
		verify(cartService, times(1)).updateCart(cartDTO);
	}

	@Test
	void getCartTotal_ShouldReturnTotal_WhenCartExists() {
		when(cartService.getCartTotal(1)).thenReturn(100.0);

		ResponseEntity<Double> response = cartResource.getCartTotal(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(100.0, response.getBody());
		verify(cartService, times(1)).getCartTotal(1);
	}

}
