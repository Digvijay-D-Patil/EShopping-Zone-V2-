package com.eshopping.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.eshopping.profile")
@ComponentScan(basePackages = "com.eshopping.profile")
@EnableDiscoveryClient
public class EShoppingZone1Application {

	public static void main(String[] args) {
		SpringApplication.run(EShoppingZone1Application.class, args);
	}

}
