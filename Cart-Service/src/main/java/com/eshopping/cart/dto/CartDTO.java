package com.eshopping.cart.dto;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CartDTO {

	private int cartId;

	@DecimalMin(value = "0.0", inclusive = true, message = "Total price must be zero or a positive value.")
	private double totalPrice;

	@NotNull(message = "Items list cannot be null.")
	@Size(min = 1, message = "There must be at least one item in the cart.")
	private List<@NotNull(message = "Item cannot be null.") ItemDTO> items;

}