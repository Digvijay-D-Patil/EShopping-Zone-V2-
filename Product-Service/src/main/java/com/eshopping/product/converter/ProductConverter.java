package com.eshopping.product.converter;

import org.springframework.stereotype.Component;

import com.eshopping.product.dto.ProductDTO;
import com.eshopping.product.entity.Product;

@Component
public class ProductConverter {

	// Convert Product entity to ProductDTO
	public static ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(product.getProductId());
		productDTO.setProductType(product.getProductType());
		productDTO.setProductName(product.getProductName());
		productDTO.setCategory(product.getCategory());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setRating(product.getRating());
		productDTO.setReview(product.getReview());
		productDTO.setImage(product.getImage());
		productDTO.setSpecification(product.getSpecification());
		return productDTO;
	}

	// Convert ProductDTO to Product entity
	public static Product dtoToEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setProductId(productDTO.getProductId());
		product.setProductType(productDTO.getProductType());
		product.setProductName(productDTO.getProductName());
		product.setCategory(productDTO.getCategory());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		product.setRating(productDTO.getRating());
		product.setReview(productDTO.getReview());
		product.setImage(productDTO.getImage());
		product.setSpecification(productDTO.getSpecification());
		return product;
	}
}
