package com.eshopping.profile.service;

import com.eshopping.profile.dto.UserProfileDTO;

public interface UserService {
	UserProfileDTO addUserProfile(UserProfileDTO userProfile);

	UserProfileDTO getUserProfile(Long profileId);

	UserProfileDTO getUserProfileByMobile(String mobileNumber);

	UserProfileDTO getUserProfileByName(String firstName);

	void deleteUserProfile(Long profileId);

}
