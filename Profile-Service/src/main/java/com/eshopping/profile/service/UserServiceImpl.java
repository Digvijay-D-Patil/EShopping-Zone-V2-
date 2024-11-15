package com.eshopping.profile.service;

import org.springframework.stereotype.Service;

import com.eshopping.profile.converter.UserProfileConverter;
import com.eshopping.profile.dto.UserProfileDTO;
import com.eshopping.profile.entity.UserProfile;
import com.eshopping.profile.exception.UserProfileNotFoundException;
import com.eshopping.profile.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository profileRepository;

	@Override
	public UserProfileDTO addUserProfile(UserProfileDTO userProfile) {
		UserProfile profile = UserProfileConverter.dtoToEntity(userProfile);
		profileRepository.save(profile);
		return userProfile;
	}

	@Override
	public UserProfileDTO getUserProfile(Long profileId) {
		UserProfile userProfile = profileRepository.findById(profileId)
				.orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found with ID: " + profileId));
		return UserProfileConverter.entityToDTO(userProfile);
	}

	@Override
	public UserProfileDTO getUserProfileByMobile(String mobileNumber) {
		UserProfile userProfile = profileRepository.findByMobileNumber(mobileNumber.trim()).orElseThrow(
				() -> new UserProfileNotFoundException("UserProfile not found with mobile number: " + mobileNumber));
		return UserProfileConverter.entityToDTO(userProfile);
	}

	@Override
	public UserProfileDTO getUserProfileByName(String firstName) {
		UserProfile userProfile = profileRepository.findByFirstName(firstName)
				.orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found with name: " + firstName));
		return UserProfileConverter.entityToDTO(userProfile);
	}

	@Override
	public void deleteUserProfile(Long profileId) {
		if (!profileRepository.existsById(profileId)) {
			throw new UserProfileNotFoundException("Cannot delete. UserProfile not found with ID: " + profileId);
		}
		profileRepository.deleteById(profileId);
	}

}
