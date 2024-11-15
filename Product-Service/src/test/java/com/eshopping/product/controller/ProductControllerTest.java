package com.eshopping.product.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.eshopping.product.dto.ProductDTO;
import com.eshopping.product.service.ProductService;

class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void addProducts_ShouldCallServiceToAddProduct() {
		ProductDTO productDTO = new ProductDTO();
		// Set productDTO properties as needed

		productController.addProducts(productDTO);

		verify(productService, times(1)).addProducts(productDTO);
	}

	@Test
	void getAllProducts_ShouldReturnListOfProducts() {
		ProductDTO product1 = new ProductDTO();
		ProductDTO product2 = new ProductDTO();

		when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));
		ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();
		List<ProductDTO> products = response.getBody();
		assertEquals(2, products.size());
		verify(productService, times(1)).getAllProducts();
	}

	@Test
	void getProductById_ShouldReturnProduct_WhenFound() {
		ProductDTO productDTO = new ProductDTO();
		when(productService.getProductById(1)).thenReturn(Optional.of(productDTO));

		ResponseEntity<ProductDTO> response = productController.getProductById(1);

		assertEquals(ResponseEntity.ok(productDTO), response);
		verify(productService, times(1)).getProductById(1);
	}

	@Test
	void getProductById_ShouldReturnNotFound_WhenNotFound() {
		when(productService.getProductById(1)).thenReturn(Optional.empty());

		ResponseEntity<ProductDTO> response = productController.getProductById(1);

		assertEquals(ResponseEntity.notFound().build(), response);
		verify(productService, times(1)).getProductById(1);
	}

	@Test
	void getProductByName_ShouldReturnProduct_WhenFound() {
		ProductDTO productDTO = new ProductDTO();
		when(productService.getProductByName("TestProduct")).thenReturn(Optional.of(productDTO));

		ResponseEntity<ProductDTO> response = productController.getProductByName("TestProduct");

		assertEquals(ResponseEntity.ok(productDTO), response);
		verify(productService, times(1)).getProductByName("TestProduct");
	}

	@Test
	void getProductByName_ShouldReturnNotFound_WhenNotFound() {
		when(productService.getProductByName("TestProduct")).thenReturn(Optional.empty());

		ResponseEntity<ProductDTO> response = productController.getProductByName("TestProduct");

		assertEquals(ResponseEntity.notFound().build(), response);
		verify(productService, times(1)).getProductByName("TestProduct");
	}

	@Test
	void updateProduct_ShouldCallServiceToUpdateProduct() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductId(1);
		when(productService.updateProducts(any(ProductDTO.class))).thenReturn(productDTO);

		ProductDTO updatedProduct = productController.updateProduct(1, productDTO);

		assertEquals(productDTO, updatedProduct);
		verify(productService, times(1)).updateProducts(productDTO);
	}

	@Test
	void deleteProduct_ShouldCallServiceToDeleteProduct() {
		doNothing().when(productService).deleteProductById(1);

		productController.deleteProduct(1);

		verify(productService, times(1)).deleteProductById(1);
	}
}