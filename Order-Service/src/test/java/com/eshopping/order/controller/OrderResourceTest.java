//package com.eshopping.order.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.eshopping.cart.dto.CartDTO;
//import com.eshopping.order.dto.OrderDTO;
//import com.eshopping.order.entity.Orders;
//import com.eshopping.order.service.OrderService;
//
//class OrderResourceTest {
//
//	@Mock
//	private OrderService orderService;
//
//	@InjectMocks
//	private OrderResource orderResource;
//
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilders.standaloneSetup(orderResource).build();
//	}
//
//	@Test
//	void placeOrder_shouldReturnOrderDTO() throws Exception {
//		// Arrange
//		CartDTO cartDTO = new CartDTO();
//		String name = "testUser";
//		OrderDTO orderDTO = new OrderDTO();
//		orderDTO.setOrderId(1);
//
//		when(orderService.placeOrder(any(CartDTO.class), eq(name))).thenReturn(orderDTO);
//
//		// Act & Assert
//		mockMvc.perform(post("/api/orders/placeOrder").contentType(MediaType.APPLICATION_JSON).param("name", name)
//				.content("{\"items\":[]}")) // Sample JSON for CartDTO
//				.andExpect(status().isOk()).andExpect(jsonPath("$.orderId").value(1));
//	}
//
//	@Test
//	void getAllOrders_shouldReturnListOfOrders() throws Exception {
//		// Arrange
//		Orders order = new Orders();
//		order.setOrderId(1);
//		List<Orders> ordersList = List.of(order);
//
//		when(orderService.getAllOrders()).thenReturn(ordersList);
//
//		// Act & Assert
//		mockMvc.perform(get("/api/orders/getAllOrders")).andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].orderId").value(1));
//	}
//
//	@Test
//	void getOrderByCustomerId_shouldReturnListOfOrders() throws Exception {
//		// Arrange
//		Orders order = new Orders();
//		order.setOrderId(1);
//		List<Orders> ordersList = List.of(order);
//
//		when(orderService.getOrderByCustomerId(1)).thenReturn(ordersList);
//
//		// Act & Assert
//		mockMvc.perform(get("/api/orders/getOrderByCustomerId/{customerId}", 1)).andExpect(status().isOk())
//				.andExpect(jsonPath("$[0].orderId").value(1));
//	}
//
//	@Test
//	void getOrderById_shouldReturnOrderDTO() throws Exception {
//		// Arrange
//		OrderDTO orderDTO = new OrderDTO();
//		orderDTO.setOrderId(1);
//
//		when(orderService.getOrderById(1)).thenReturn(orderDTO);
//
//		// Act & Assert
//		mockMvc.perform(get("/api/orders/getOrderById/{orderId}", 1)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.orderId").value(1));
//	}
//
//	@Test
//	void cancelOrder_shouldReturnSuccessMessage() throws Exception {
//		// Act & Assert
//		mockMvc.perform(put("/api/orders/cancelOrder/{orderId}", 1)).andExpect(status().isOk())
//				.andExpect(content().string("Order canceled successfully"));
//	}
//
//}
