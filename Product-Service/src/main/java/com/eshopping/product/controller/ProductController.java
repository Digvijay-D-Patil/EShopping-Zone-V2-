package com.eshopping.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.product.dto.ProductDTO;
import com.eshopping.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Add a new product")
	@PostMapping
	public ResponseEntity<ProductDTO> addProducts(@Valid @RequestBody ProductDTO productDTO) {
		ProductDTO product = productService.addProducts(productDTO);
		return ResponseEntity.ok(product); // HTTP 200 OK with the product in the response body
	}

	@Operation(summary = "Retrieve all products")
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<ProductDTO> products = productService.getAllProducts();
		return ResponseEntity.ok(products); // HTTP 200 OK with the list of products in the response body
	}

	@Operation(summary = "Retrieve a product by its ID")
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
		Optional<ProductDTO> productDTO = productService.getProductById(id);
		return productDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Retrieve a product by its name")
	@GetMapping("/name/{productName}")
	public ResponseEntity<ProductDTO> getProductByName(@PathVariable String productName) {
		Optional<ProductDTO> productDTO = productService.getProductByName(productName);
		return productDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Retrieve products by category")
	@GetMapping("/category/{category}")
	public List<ProductDTO> getProductByCategory(@PathVariable String category) {
		return productService.getProductByCategory(category);
	}

	@Operation(summary = "Retrieve products by type")
	@GetMapping("/type/{productType}")
	public List<ProductDTO> getProductByType(@PathVariable String productType) {
		return productService.getProductByType(productType);
	}

	@Operation(summary = "Update an existing product")
	@PutMapping("/{productId}")
	public ProductDTO updateProduct(@PathVariable int productId, @Valid @RequestBody ProductDTO productDTO) {
		productDTO.setProductId(productId); // Ensures DTO has the correct ID
		return productService.updateProducts(productDTO);
	}

	@Operation(summary = "Delete a product by its ID")
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProductById(id);
	}
}
