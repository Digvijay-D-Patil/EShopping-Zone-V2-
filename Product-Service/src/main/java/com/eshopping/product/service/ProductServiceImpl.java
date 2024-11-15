package com.eshopping.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.product.converter.ProductConverter;
import com.eshopping.product.dto.ProductDTO;
import com.eshopping.product.entity.Product;
import com.eshopping.product.exception.ProductNotFoundException;
import com.eshopping.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDTO addProducts(ProductDTO productDTO) {
		Product product = ProductConverter.dtoToEntity(productDTO);
		productRepository.save(product);
		return productDTO;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(ProductConverter::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> getProductById(int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			throw new ProductNotFoundException("Product with ID " + id + " not found");
		}
		return product.map(ProductConverter::entityToDTO);
	}

	@Override
	public Optional<ProductDTO> getProductByName(String productName) {
		Optional<Product> product = productRepository.findByProductName(productName);
		if (product.isEmpty()) {
			throw new ProductNotFoundException("Product with name " + productName + " not found");
		}
		return product.map(ProductConverter::entityToDTO);
	}

	@Override
	public ProductDTO updateProducts(ProductDTO productDTO) {
		if (!productRepository.existsById(productDTO.getProductId())) {
			throw new ProductNotFoundException("Product with ID " + productDTO.getProductId() + " not found");
		}
		Product product = ProductConverter.dtoToEntity(productDTO);
		Product updatedProduct = productRepository.save(product);
		return ProductConverter.entityToDTO(updatedProduct);
	}

	@Override
	public void deleteProductById(int id) {
		if (!productRepository.existsById(id)) {
			throw new ProductNotFoundException("Product with ID " + id + " not found");
		}
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductDTO> getProductByCategory(String category) {
		List<Product> products = productRepository.findByCategory(category);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products found in category " + category);
		}
		return products.stream().map(ProductConverter::entityToDTO).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getProductByType(String productType) {
		List<Product> products = productRepository.findByProductType(productType);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("No products found of type " + productType);
		}
		return products.stream().map(ProductConverter::entityToDTO).collect(Collectors.toList());
	}
}
