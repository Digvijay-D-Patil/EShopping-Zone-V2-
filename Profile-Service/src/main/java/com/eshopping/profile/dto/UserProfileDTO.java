package com.eshopping.profile.dto;

import java.time.LocalDate;
import java.util.List;

import com.eshopping.profile.enumeration.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserProfileDTO {
	@NotNull(message = "Profile ID cannot be null")
	private Long profileId;

	@NotBlank(message = "First name cannot be blank")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	private String firstName;

	@NotBlank(message = "Last name cannot be blank")
	@Size(max = 50, message = "Last name cannot exceed 50 characters")
	private String lastName;

	@Size(max = 500, message = "About section cannot exceed 500 characters")
	private String about;

	@NotBlank(message = "Gender cannot be blank")
	private String gender;

	@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Mobile number must be between 10 and 15 digits")
	private String mobileNumber;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	private String email;

	@Past(message = "Date of birth must be a past date")
	private LocalDate dob;

	@NotNull(message = "Addresses cannot be null")
	private List<AddressDTO> addresses;

	@NotNull(message = "Role cannot be null")
	private Role role;
}
