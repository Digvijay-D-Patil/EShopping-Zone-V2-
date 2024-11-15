package com.eshopping.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eshopping.order.entity.Orders;
import com.eshopping.order.exception.OrderNotFoundException;
import com.eshopping.order.repository.OrderRepository;
import com.eshopping.profile.service.UserService;

class OrderServiceImplTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private UserService userService;

	@InjectMocks
	private OrderServiceImpl orderService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllOrders_shouldReturnListOfOrders() {
		// Arrange
		Orders order = new Orders();
		order.setOrderId(1);
		when(orderRepository.findAll()).thenReturn(List.of(order));

		// Act
		List<Orders> result = orderService.getAllOrders();

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void getAllOrders_shouldThrowExceptionWhenNoOrders() {
		// Arrange
		when(orderRepository.findAll()).thenReturn(new ArrayList<>());

		// Act & Assert
		assertThrows(OrderNotFoundException.class, () -> orderService.getAllOrders());
		verify(orderRepository, times(1)).findAll();
	}

	@Test
	void getOrderByCustomerId_shouldReturnOrders() {
		// Arrange
		Orders order = new Orders();
		order.setOrderId(1);
		when(orderRepository.findByCustomerId(1)).thenReturn(List.of(order));

		// Act
		List<Orders> result = orderService.getOrderByCustomerId(1);

		// Assert
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(orderRepository, times(1)).findByCustomerId(1);
	}

	@Test
	void getOrderByCustomerId_shouldThrowExceptionWhenNoOrders() {
		// Arrange
		when(orderRepository.findByCustomerId(1)).thenReturn(new ArrayList<>());

		// Act & Assert
		assertThrows(OrderNotFoundException.class, () -> orderService.getOrderByCustomerId(1));
		verify(orderRepository, times(1)).findByCustomerId(1);
	}

	@Test
	void getOrderById_shouldThrowExceptionWhenOrderNotFound() {
		// Arrange
		when(orderRepository.findById(1)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1));
		verify(orderRepository, times(1)).findById(1);
	}

	@Test
	void cancelOrder_shouldCancelOrderSuccessfully() {
		// Arrange
		Orders order = new Orders();
		order.setOrderId(1);
		when(orderRepository.findById(1)).thenReturn(Optional.of(order));

		// Act
		orderService.cancelOrder(1);

		// Assert
		verify(orderRepository, times(1)).save(order);
		assertEquals("Canceled", order.getOrderStatus());
	}

	@Test
	void cancelOrder_shouldThrowExceptionWhenOrderNotFound() {
		// Arrange
		when(orderRepository.findById(1)).thenReturn(Optional.empty());

		// Act & Assert
		assertThrows(OrderNotFoundException.class, () -> orderService.cancelOrder(1));
		verify(orderRepository, times(1)).findById(1);
	}
}
