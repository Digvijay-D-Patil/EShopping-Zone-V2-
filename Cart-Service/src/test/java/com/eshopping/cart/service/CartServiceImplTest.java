package com.eshopping.cart.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eshopping.cart.converter.CartConverter;
import com.eshopping.cart.dto.CartDTO;
import com.eshopping.cart.exception.CartNotFoundException;
import com.eshopping.cart.repository.CartRepository;

class CartServiceImplTest {

	@Mock
	private CartRepository cartRepository;

	@Mock
	private CartConverter cartConverter;

	@InjectMocks
	private CartServiceImpl cartService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetCartById_NotFound() {
		when(cartRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(CartNotFoundException.class, () -> cartService.getCartById(1));
	}

	@Test
	void testUpdateCart_NotFound() {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartId(1);
		cartDTO.setTotalPrice(120.0);

		when(cartRepository.existsById(1)).thenReturn(false);

		assertThrows(CartNotFoundException.class, () -> cartService.updateCart(cartDTO));
	}

	@Test
	void testGetCartTotal_NotFound() {
		when(cartRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(CartNotFoundException.class, () -> cartService.getCartTotal(1));
	}
}
