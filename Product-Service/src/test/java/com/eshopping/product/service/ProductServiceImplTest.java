package com.eshopping.product.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.eshopping.product.converter.ProductConverter;
import com.eshopping.product.dto.ProductDTO;
import com.eshopping.product.entity.Product;
import com.eshopping.product.exception.ProductNotFoundException;
import com.eshopping.product.repository.ProductRepository;

class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	private Product product;
	private ProductDTO productDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		product = new Product();
		product.setProductId(1);
		product.setProductName("Sample Product");
		product.setProductType("Electronics");
		product.setCategory("Gadgets");
		product.setPrice(299.99);
		product.setDescription("A sample product description");

		productDTO = ProductConverter.entityToDTO(product);
	}

	@Test
	void testAddProducts() {
		when(productRepository.save(any(Product.class))).thenReturn(product);

		assertDoesNotThrow(() -> productService.addProducts(productDTO));
		verify(productRepository, times(1)).save(any(Product.class));
	}

	@Test
	void testGetAllProducts() {
		when(productRepository.findAll()).thenReturn(Arrays.asList(product));

		List<ProductDTO> products = productService.getAllProducts();
		assertEquals(1, products.size());
		assertEquals("Sample Product", products.get(0).getProductName());

		verify(productRepository, times(1)).findAll();
	}

	@Test
	void testGetProductById_Found() {
		when(productRepository.findById(1)).thenReturn(Optional.of(product));

		Optional<ProductDTO> foundProduct = productService.getProductById(1);
		assertTrue(foundProduct.isPresent());
		assertEquals("Sample Product", foundProduct.get().getProductName());

		verify(productRepository, times(1)).findById(1);
	}

	@Test
	void testGetProductById_NotFound() {
		when(productRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1));
		verify(productRepository, times(1)).findById(1);
	}

	@Test
	void testGetProductByName_Found() {
		when(productRepository.findByProductName("Sample Product")).thenReturn(Optional.of(product));

		Optional<ProductDTO> foundProduct = productService.getProductByName("Sample Product");
		assertTrue(foundProduct.isPresent());
		assertEquals("Sample Product", foundProduct.get().getProductName());

		verify(productRepository, times(1)).findByProductName("Sample Product");
	}

	@Test
	void testGetProductByName_NotFound() {
		when(productRepository.findByProductName("Sample Product")).thenReturn(Optional.empty());

		assertThrows(ProductNotFoundException.class, () -> productService.getProductByName("Sample Product"));
		verify(productRepository, times(1)).findByProductName("Sample Product");
	}

	@Test
	void testUpdateProducts_Found() {
		when(productRepository.existsById(1)).thenReturn(true);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		ProductDTO updatedProduct = productService.updateProducts(productDTO);
		assertNotNull(updatedProduct);
		assertEquals("Sample Product", updatedProduct.getProductName());

		verify(productRepository, times(1)).existsById(1);
		verify(productRepository, times(1)).save(any(Product.class));
	}

	@Test
	void testUpdateProducts_NotFound() {
		when(productRepository.existsById(1)).thenReturn(false);

		assertThrows(ProductNotFoundException.class, () -> productService.updateProducts(productDTO));
		verify(productRepository, times(1)).existsById(1);
		verify(productRepository, times(0)).save(any(Product.class));
	}

	@Test
	void testDeleteProductById_Found() {
		when(productRepository.existsById(1)).thenReturn(true);
		doNothing().when(productRepository).deleteById(1);

		assertDoesNotThrow(() -> productService.deleteProductById(1));
		verify(productRepository, times(1)).existsById(1);
		verify(productRepository, times(1)).deleteById(1);
	}

	@Test
	void testDeleteProductById_NotFound() {
		when(productRepository.existsById(1)).thenReturn(false);

		assertThrows(ProductNotFoundException.class, () -> productService.deleteProductById(1));
		verify(productRepository, times(1)).existsById(1);
		verify(productRepository, times(0)).deleteById(1);
	}

	@Test
	void testGetProductByCategory_Found() {
		when(productRepository.findByCategory("Gadgets")).thenReturn(Arrays.asList(product));

		List<ProductDTO> products = productService.getProductByCategory("Gadgets");
		assertEquals(1, products.size());
		assertEquals("Sample Product", products.get(0).getProductName());

		verify(productRepository, times(1)).findByCategory("Gadgets");
	}

	@Test
	void testGetProductByCategory_NotFound() {
		when(productRepository.findByCategory("NonExistentCategory")).thenReturn(Arrays.asList());

		assertThrows(ProductNotFoundException.class, () -> productService.getProductByCategory("NonExistentCategory"));
		verify(productRepository, times(1)).findByCategory("NonExistentCategory");
	}

	@Test
	void testGetProductByType_Found() {
		when(productRepository.findByProductType("Electronics")).thenReturn(Arrays.asList(product));

		List<ProductDTO> products = productService.getProductByType("Electronics");
		assertEquals(1, products.size());
		assertEquals("Sample Product", products.get(0).getProductName());

		verify(productRepository, times(1)).findByProductType("Electronics");
	}

	@Test
	void testGetProductByType_NotFound() {
		when(productRepository.findByProductType("NonExistentType")).thenReturn(Arrays.asList());

		assertThrows(ProductNotFoundException.class, () -> productService.getProductByType("NonExistentType"));
		verify(productRepository, times(1)).findByProductType("NonExistentType");
	}
}