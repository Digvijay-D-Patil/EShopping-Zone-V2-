package com.eshopping.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eshopping.order.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findByCustomerId(Integer customerId);

	Orders findFirstByOrderByOrderIdDesc();
}
