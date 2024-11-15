package com.eshopping.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.cart.converter.CartConverter;
import com.eshopping.cart.dto.CartDTO;
import com.eshopping.cart.entity.Cart;
import com.eshopping.cart.exception.CartNotFoundException;
import com.eshopping.cart.feignclient.ProductFeignClient;
import com.eshopping.cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;

	@Autowired
	private ProductFeignClient productFeignClient;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public CartDTO getCartById(int cartId) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));
		cart.getItems().forEach(d -> {
			// d.setProductDTO(productFeignClient.getProductById(d.getProductId()));

		});
		return CartConverter.entityToDTO(cart);
	}

	@Override
	public List<CartDTO> getAllCarts() {
		List<CartDTO> getAllCartsObj = cartRepository.findAll().stream().map(CartConverter::entityToDTO)
				.collect(Collectors.toList());
		getAllCartsObj.forEach(data -> {
			data.getItems().forEach(d -> {
				d.setProductDTO(productFeignClient.getProductById(d.getProductId()));
			});

		});
		return getAllCartsObj;
	}

	@Override
	public CartDTO addCart(CartDTO cartDTO) {
		Cart cart = CartConverter.dtoToEntity(cartDTO);
		Cart savedCart = cartRepository.save(cart);
		return CartConverter.entityToDTO(savedCart);
	}

	@Override
	public CartDTO updateCart(CartDTO cartDTO) {

		if (cartDTO.getCartId() == 0 || !cartRepository.existsById(cartDTO.getCartId())) {
			throw new CartNotFoundException("Cart with ID " + cartDTO.getCartId() + " not found");
		}

		Cart cart = CartConverter.dtoToEntity(cartDTO);
		Cart updatedCart = cartRepository.save(cart);
		return CartConverter.entityToDTO(updatedCart);
	}

	@Override
	public double getCartTotal(int cartId) {
		CartDTO cart = getCartById(cartId);
		return cart.getTotalPrice();
	}
}
