package com.eshopping.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	Optional<Product> findByProductName(String productName);

	List<Product> findByCategory(String category);

	List<Product> findByProductType(String productType);
}
