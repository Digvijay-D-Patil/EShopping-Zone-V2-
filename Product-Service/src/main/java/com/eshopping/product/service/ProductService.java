package com.eshopping.product.service;

import java.util.List;
import java.util.Optional;

import com.eshopping.product.dto.ProductDTO;

public interface ProductService {

	ProductDTO addProducts(ProductDTO product);

	List<ProductDTO> getAllProducts();

	Optional<ProductDTO> getProductById(int id);

	Optional<ProductDTO> getProductByName(String productName);

	ProductDTO updateProducts(ProductDTO product);

	void deleteProductById(int id);

	List<ProductDTO> getProductByCategory(String category);

	List<ProductDTO> getProductByType(String productType);
}
