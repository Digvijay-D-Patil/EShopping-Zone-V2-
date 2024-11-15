package com.eshopping.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiProfileConfig {

	@Bean
	public OpenAPI customOrderAPI() {
		Info info = new Info();
		info.setTitle("EShopping Zone - Cart");
		info.setVersion("1.0");
		info.description("API documentation for the EShopping Zone application");
		return new OpenAPI().info(info);
	}

}