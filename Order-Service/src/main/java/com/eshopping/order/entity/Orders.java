package com.eshopping.order.entity;

import java.time.LocalDate;

import com.eshopping.profile.entity.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private LocalDate orderDate;
	private long customerId;
	private double amountPaid;
	private String modeOfPayment;
	private String orderStatus;
	private int quantity;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "address_id")
//	private Address address;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "product_id")
//	private Product product;

	public Address setAddress(com.eshopping.profile.entity.Address dto) {
		// TODO Auto-generated method stub
		Address address = new Address();
		address.setAddressId(dto.getAddressId());
		address.setHouseNumber(dto.getHouseNumber());
		address.setStreetName(dto.getStreetName());
		address.setColonyName(dto.getColonyName());
		address.setCity(dto.getCity());
		address.setState(dto.getState());
		address.setPinCode(dto.getPinCode());
		return address;
	}

}
