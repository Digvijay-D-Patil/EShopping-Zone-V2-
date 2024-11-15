package com.eshopping.order.converter;

import com.eshopping.order.dto.OrderDTO;
import com.eshopping.order.entity.Orders;

public class OrderMapper {

	// Convert Orders entity to OrderDTO
	public static OrderDTO entityToDTO(Orders order) {
		if (order == null) {
			return null;
		}

		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setOrderDate(order.getOrderDate());
		orderDTO.setCustomerId(order.getCustomerId());
		orderDTO.setAmountPaid(order.getAmountPaid());
		orderDTO.setModeOfPayment(order.getModeOfPayment());
		orderDTO.setOrderStatus(order.getOrderStatus());

		// Map Address and Product to their respective DTOs

		return orderDTO;
	}

	// Convert OrderDTO to Orders entity
	public static Orders dtoToEntity(OrderDTO orderDTO) {
		if (orderDTO == null) {
			return null;
		}

		Orders order = new Orders();
		order.setOrderId(orderDTO.getOrderId());
		order.setOrderDate(orderDTO.getOrderDate());
		order.setCustomerId(orderDTO.getCustomerId());
		order.setAmountPaid(orderDTO.getAmountPaid());
		order.setModeOfPayment(orderDTO.getModeOfPayment());
		order.setOrderStatus(orderDTO.getOrderStatus());
		order.setQuantity(orderDTO.getCartDTO().getItems().size());

		// Map AddressDTO and ProductDTO to their respective entities

		return order;
	}
}
