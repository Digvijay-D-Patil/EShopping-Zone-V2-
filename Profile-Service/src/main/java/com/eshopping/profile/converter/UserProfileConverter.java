package com.eshopping.profile.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.eshopping.profile.dto.AddressDTO;
import com.eshopping.profile.dto.UserProfileDTO;
import com.eshopping.profile.entity.Address;
import com.eshopping.profile.entity.UserProfile;

@Component
public class UserProfileConverter {

	// Convert UserProfile entity to UserProfileDTO
	public static UserProfileDTO entityToDTO(UserProfile userProfile) {
		UserProfileDTO dto = new UserProfileDTO();
		dto.setProfileId(userProfile.getProfileId());
		dto.setFirstName(userProfile.getFirstName());
		dto.setLastName(userProfile.getLastName());
		dto.setAbout(userProfile.getAbout());
		dto.setGender(userProfile.getGender());
		dto.setMobileNumber(userProfile.getMobileNumber());
		dto.setEmail(userProfile.getEmail());
		dto.setDob(userProfile.getDob());
		dto.setRole(userProfile.getRole());

		// Convert List<Address> to List<AddressDTO>
		if (userProfile.getAddresses() != null) {
			dto.setAddresses(userProfile.getAddresses().stream().map(UserProfileConverter::addressEntityToDTO)
					.collect(Collectors.toList()));
		}

		return dto;
	}

	// Convert UserProfileDTO to UserProfile entity
	public static UserProfile dtoToEntity(UserProfileDTO dto) {
		UserProfile userProfile = new UserProfile();
		userProfile.setProfileId(dto.getProfileId());
		userProfile.setFirstName(dto.getFirstName());
		userProfile.setLastName(dto.getLastName());
		userProfile.setAbout(dto.getAbout());
		userProfile.setGender(dto.getGender());
		userProfile.setMobileNumber(dto.getMobileNumber());
		userProfile.setEmail(dto.getEmail());
		userProfile.setDob(dto.getDob());
		userProfile.setRole(dto.getRole());

		// Convert List<AddressDTO> to List<Address>
		if (dto.getAddresses() != null) {
			userProfile.setAddresses(dto.getAddresses().stream().map(UserProfileConverter::addressDTOToEntity)
					.collect(Collectors.toList()));
		}

		return userProfile;
	}

	// Helper method to convert Address entity to AddressDTO
	public static AddressDTO addressEntityToDTO(Address address) {
		AddressDTO dto = new AddressDTO();
		dto.setAddressId(address.getAddressId());
		dto.setHouseNumber(address.getHouseNumber());
		dto.setStreetName(address.getStreetName());
		dto.setColonyName(address.getColonyName());
		dto.setCity(address.getCity());
		dto.setState(address.getState());
		dto.setPinCode(address.getPinCode());
		return dto;
	}

	// Helper method to convert AddressDTO to Address entity
	public static Address addressDTOToEntity(AddressDTO dto) {
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
