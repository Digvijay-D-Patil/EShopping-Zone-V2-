package com.eshopping.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.profile.converter.UserProfileConverter;
import com.eshopping.profile.dto.UserProfileDTO;
import com.eshopping.profile.entity.UserProfile;
import com.eshopping.profile.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ProfileController {

	private final UserService userService;

	@Operation(summary = "Add a new user profile")
	@PostMapping
	public ResponseEntity<UserProfileDTO> addUserProfile(@Valid @RequestBody UserProfile userProfile) {
		UserProfileDTO userProfileDTO = UserProfileConverter.entityToDTO(userProfile); // Convert entity to DTO
		UserProfileDTO createdProfile = userService.addUserProfile(userProfileDTO);
		return ResponseEntity.ok(createdProfile);
	}

	@Operation(summary = "Get a user profile by its ID")
	@GetMapping("/{profileId}")
	public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long profileId) {
		UserProfileDTO userProfile = userService.getUserProfile(profileId);
		return ResponseEntity.ok(userProfile);
	}

	@Operation(summary = "Get a user profile by mobile number")
	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<UserProfileDTO> getUserProfileByMobile(@PathVariable String mobileNumber) {
		UserProfileDTO userProfile = userService.getUserProfileByMobile(mobileNumber);
		return ResponseEntity.ok(userProfile);
	}

	@Operation(summary = "Get a user profile by first name")
	@GetMapping("/name")
	public ResponseEntity<UserProfileDTO> getUserProfileByName(@PathVariable String firstName) {
		UserProfileDTO userProfile = userService.getUserProfileByName(firstName);
		return ResponseEntity.ok(userProfile);
	}

	@Operation(summary = "Delete a user profile by its ID")
	@DeleteMapping("/{profileId}")
	public ResponseEntity<Void> deleteUserProfile(@PathVariable Long profileId) {
		userService.deleteUserProfile(profileId);
		return ResponseEntity.noContent().build();
	}
}
