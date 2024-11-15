package com.eshopping.profile.entity;

import java.time.LocalDate;
import java.util.List;

import com.eshopping.profile.enumeration.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long profileId;
	private String firstName;
	private String lastName;
	private String about;
	private String gender;
	private String mobileNumber;
	private String email;
	private LocalDate dob;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id") // Maps the foreign key column in Address table
	private List<Address> addresses;

	@Enumerated(EnumType.STRING)
	private Role role;

}
