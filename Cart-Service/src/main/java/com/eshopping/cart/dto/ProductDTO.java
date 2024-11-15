package com.eshopping.cart.dto;


import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDTO {

	@Min(value = 1, message = "Product ID must be greater than 0")
	private int productId;

	@NotBlank(message = "Product type cannot be blank")
	@Size(max = 50, message = "Product type must be less than 50 characters")
	private String productType;

	@NotBlank(message = "Product name cannot be blank")
	@Size(max = 100, message = "Product name must be less than 100 characters")
	private String productName;

	@NotBlank(message = "Category cannot be blank")
	@Size(max = 50, message = "Category must be less than 50 characters")
	private String category;

	@NotNull(message = "Price cannot be null")

	private double price;

	@NotBlank(message = "Description cannot be blank")
	@Size(max = 500, message = "Description must be less than 500 characters")
	private String description;

	@NotEmpty(message = "Rating map cannot be empty")
	private Map<Integer, Double> rating;

	@NotEmpty(message = "Review map cannot be empty")
	private Map<Integer, String> review;

	@NotEmpty(message = "Image list cannot be empty")
	@Size(max = 10, message = "Cannot have more than 10 images")
	private List<String> image;

	@NotEmpty(message = "Specification map cannot be empty")
	private Map<String, String> specification;
}

//	private int productId;
//	private String productType;
//	private String productName;
//	private String category;
//	private double price;
//	private String description;
//	private Map<Integer, Double> rating;
//	private Map<Integer, String> review;
//	private List<String> image;
//	private Map<String, String> specification;
//}