package com.eshopping.order.service;

import java.util.List;

import com.eshopping.cart.dto.CartDTO;
import com.eshopping.order.dto.OrderDTO;
import com.eshopping.order.entity.Orders;

public interface OrderService {
	OrderDTO placeOrder(CartDTO cart, String name);

	List<Orders> getAllOrders();

	List<Orders> getOrderByCustomerId(int customerId);

	public OrderDTO getOrderById(int orderId);

	void cancelOrder(int orderId);

}