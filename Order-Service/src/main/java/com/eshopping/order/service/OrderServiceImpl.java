package com.eshopping.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.cart.dto.ItemDTO;
import com.eshopping.order.converter.OrderMapper;
import com.eshopping.order.dto.OrderDTO;
import com.eshopping.order.entity.Orders;
import com.eshopping.order.exception.OrderNotFoundException;
import com.eshopping.order.repository.OrderRepository;
import com.eshopping.product.entity.Product;
import com.eshopping.profile.converter.UserProfileConverter;
import com.eshopping.profile.dto.UserProfileDTO;
import com.eshopping.profile.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserService userService;

	@Override
	public OrderDTO placeOrder(OrderDTO orderDTO, String name) {
		// Fetch user profile
		UserProfileDTO userProfileByName = userService.getUserProfile(orderDTO.getCustomerId());
		if (userProfileByName == null) {
			throw new OrderNotFoundException("User not found");
		}

		// Calculate total price and validate products in the cart
		double totalPrice = 0.0;
		for (ItemDTO item : orderDTO.getCartDTO().getItems()) {
			Optional<Product> productOpt = Optional.empty();
			if (productOpt.isPresent()) {
				Product product = productOpt.get();
				totalPrice += product.getPrice() * item.getQuantity();
			} else {
				throw new OrderNotFoundException("Product not found for ID: " + item.getId());
			}
		}

		// Create and save order
		Orders order = new Orders();
		order.setCustomerId(userProfileByName.getProfileId());
		order.setOrderDate(LocalDate.now());
		order.setAmountPaid(totalPrice);
		if (userProfileByName.getAddresses() != null && userProfileByName.getAddresses().size() > 0)
			order.setAddress(UserProfileConverter.addressDTOToEntity(userProfileByName.getAddresses().get(0)));
		order.setOrderStatus("Pending");
		orderRepository.save(order);

		return OrderMapper.entityToDTO(order);
	}

	@Override
	public List<Orders> getAllOrders() {
		List<Orders> orders = orderRepository.findAll();
		if (orders.isEmpty()) {
			throw new OrderNotFoundException("No orders found");
		}
		return orders;
	}

	@Override
	public List<Orders> getOrderByCustomerId(int customerId) {
		List<Orders> orders = orderRepository.findByCustomerId(customerId);
		if (orders.isEmpty()) {
			throw new OrderNotFoundException("No orders found for customer ID: " + customerId);
		}
		return orders;
	}

	@Override
	public OrderDTO getOrderById(int orderId) {
		return orderRepository.findById(orderId).map(OrderMapper::entityToDTO)
				.orElseThrow(() -> new OrderNotFoundException("Order not found for ID: " + orderId));
	}

	@Override
	public void cancelOrder(int orderId) {
		Optional<Orders> orderOpt = orderRepository.findById(orderId);
		if (orderOpt.isPresent()) {
			Orders order = orderOpt.get();
			order.setOrderStatus("Canceled");
			orderRepository.save(order);
		} else {
			throw new OrderNotFoundException("Order not found for ID: " + orderId);
		}
	}
}
