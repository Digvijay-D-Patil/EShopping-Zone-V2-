package com.eshopping.cart.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eshopping.cart.dto.ProductDTO;

@FeignClient(name = "ProductFeignClient", url = "http://localhost:8092/api/products")
public interface ProductFeignClient {

	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable int id);

}
