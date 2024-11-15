package com.eshopping.product.entity;

import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	private String productType;
	private String productName;
	private String category;
	private double price;
	private String description;

	@ElementCollection
	@CollectionTable(name = "product_rating", joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "rating_id")
	@Column(name = "rating_value")
	private Map<Integer, Double> rating;

	@ElementCollection
	@CollectionTable(name = "product_review", joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "review_id")
	@Column(name = "review_text")
	private Map<Integer, String> review;

	@ElementCollection
	private List<String> image;

	@ElementCollection
	@CollectionTable(name = "product_specification", joinColumns = @JoinColumn(name = "product_id"))
	@MapKeyColumn(name = "spec_key")
	@Column(name = "spec_value")
	private Map<String, String> specification;

}
