package com.eshopping.cart.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;

	private double totalPrice;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id")
	private List<Items> items;

	public Cart() {
	}

	public Cart(int cartId, List<Items> items) {
		this.cartId = cartId;
		this.items = items;
		this.totalPrice = calculateTotalPrice();
	}

	private double calculateTotalPrice() {
		return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
	}
}
