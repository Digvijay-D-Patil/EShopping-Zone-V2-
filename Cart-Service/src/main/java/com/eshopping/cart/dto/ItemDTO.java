package com.eshopping.cart.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemDTO {

	private int id; // Typically this might be an auto-generated field, so no validation is
					// necessary here.

	@NotBlank(message = "Product name cannot be blank.")
	private String productName;

	@NotNull(message = "Price cannot be null.")
	@DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero.")
	private double price;

	@Min(value = 1, message = "Quantity must be at least 1.")
	private int quantity;
}