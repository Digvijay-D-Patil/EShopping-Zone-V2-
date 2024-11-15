package com.eshopping.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.order.dto.OrderDTO;
import com.eshopping.order.entity.Orders;
import com.eshopping.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@PostMapping("/placeOrder")
	@Operation(summary = "Place a new order based on cart details and user name")
	public ResponseEntity<OrderDTO> placeOrder(@Valid @RequestBody OrderDTO cart, @RequestParam String name) {
		OrderDTO order = orderService.placeOrder(cart, name);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/getAllOrders")
	@Operation(summary = "Retrieve a list of all orders")
	public ResponseEntity<List<Orders>> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@GetMapping("/getOrderByCustomerId/{customerId}")
	@Operation(summary = "Retrieve orders by customer ID")
	public ResponseEntity<List<Orders>> getOrderByCustomerId(@PathVariable int customerId) {
		return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
	}

	@GetMapping("/getOrderById/{orderId}")
	@Operation(summary = "Retrieve an order by its ID")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable int orderId) {

		OrderDTO order = orderService.getOrderById(orderId);
		return ResponseEntity.ok(order); // Return the order wrapped in a ResponseEntity with status 200 OK

	}

	@PutMapping("/cancelOrder/{orderId}")
	@Operation(summary = "Cancel an order by its ID")
	public ResponseEntity<String> cancelOrder(@PathVariable int orderId) {
		orderService.cancelOrder(orderId);
		return ResponseEntity.ok("Order canceled successfully");
	}
}
