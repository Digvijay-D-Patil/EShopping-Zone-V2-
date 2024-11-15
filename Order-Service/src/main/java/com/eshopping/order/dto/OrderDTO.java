package com.eshopping.order.dto;

import java.time.LocalDate;

import com.eshopping.cart.dto.CartDTO;
import com.eshopping.profile.dto.UserProfileDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDTO {

	@Min(value = 1, message = "Order ID must be a positive integer")
	private int orderId;

	@NotNull(message = "Order date cannot be null")
	@PastOrPresent(message = "Order date cannot be in the future")
	private LocalDate orderDate;

	@Min(value = 1, message = "Customer ID must be a positive integer")
	private long customerId;

	@DecimalMin(value = "0.0", inclusive = false, message = "Amount paid must be positive")
	private double amountPaid;

	@NotNull(message = "Mode of payment cannot be null")
	@Size(min = 3, max = 20, message = "Mode of payment must be between 3 and 20 characters")
	private String modeOfPayment;

	@NotNull(message = "Order status cannot be null")
	@Size(min = 3, max = 20, message = "Order status must be between 3 and 20 characters")
	private String orderStatus;

	private CartDTO cartDTO;

	private UserProfileDTO profileDTO;
	/*
	 * @Min(value = 1, message = "Quantity must be at least 1") private int
	 * quantity;
	 */
	/*
	 * @NotNull(message = "Address cannot be null") private AddressDTO address;
	 */

	/*
	 * @NotNull(message = "Product cannot be null") private ProductDTO product;
	 */

}
