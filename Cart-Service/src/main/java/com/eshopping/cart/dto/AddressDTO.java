package com.eshopping.cart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDTO {
	@NotNull(message = "Address ID cannot be null")
	private Long addressId;

	@NotBlank(message = "House number cannot be blank")
	@Size(max = 20, message = "House number cannot exceed 20 characters")
	private String houseNumber;

	@NotBlank(message = "Street name cannot be blank")
	@Size(max = 100, message = "Street name cannot exceed 100 characters")
	private String streetName;

	@NotBlank(message = "Colony name cannot be blank")
	@Size(max = 100, message = "Colony name cannot exceed 100 characters")
	private String colonyName;

	@NotBlank(message = "City cannot be blank")
	@Size(max = 50, message = "City cannot exceed 50 characters")
	private String city;

	@NotBlank(message = "State cannot be blank")
	@Size(max = 50, message = "State cannot exceed 50 characters")
	private String state;

	@NotBlank(message = "Pin code cannot be blank")
	@Pattern(regexp = "^[0-9]{5,6}$", message = "Pin code must be 5 or 6 digits")
	private String pinCode;
}
