package com.eshopping.cart.converter;

import java.util.stream.Collectors;

import com.eshopping.cart.dto.CartDTO;
import com.eshopping.cart.dto.ItemDTO;
import com.eshopping.cart.entity.Cart;
import com.eshopping.cart.entity.Items;

public class CartConverter {

	// Convert Cart entity to CartDTO
	public static CartDTO entityToDTO(Cart cart) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setCartId(cart.getCartId());
		cartDTO.setTotalPrice(cart.getTotalPrice());
		cartDTO.setItems(cart.getItems().stream().map(CartConverter::itemEntityToDTO).collect(Collectors.toList()));
		return cartDTO;
	}

	// Convert CartDTO to Cart entity
	public static Cart dtoToEntity(CartDTO cartDTO) {
		Cart cart = new Cart();
		cart.setCartId(cartDTO.getCartId());
		cart.setTotalPrice(cartDTO.getTotalPrice());
		cart.setItems(cartDTO.getItems().stream().map(CartConverter::itemDtoToEntity).collect(Collectors.toList()));
		return cart;
	}

	// Convert Items entity to ItemDTO
	public static ItemDTO itemEntityToDTO(Items item) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setId(item.getId());
		itemDTO.setProductId(item.getProductId());
//		itemDTO.setProductName(item.getProductName());
		itemDTO.setPrice(item.getPrice());
		itemDTO.setQuantity(item.getQuantity());
		return itemDTO;
	}

	// Convert ItemDTO to Items entity
	public static Items itemDtoToEntity(ItemDTO itemDTO) {
		Items item = new Items();
		item.setId(itemDTO.getId());
		item.setProductId(itemDTO.getProductId());
//		item.setProductName(itemDTO.getProductName());
		item.setPrice(itemDTO.getPrice());
		item.setQuantity(itemDTO.getQuantity());
		return item;
	}
}
