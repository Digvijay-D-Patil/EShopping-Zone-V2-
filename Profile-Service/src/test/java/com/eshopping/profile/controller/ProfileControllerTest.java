package com.eshopping.profile.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eshopping.profile.dto.UserProfileDTO;
import com.eshopping.profile.entity.UserProfile;
import com.eshopping.profile.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

class ProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private ProfileController profileController;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	void addUserProfile_ShouldReturnCreatedProfile() throws Exception {
		UserProfile userProfile = new UserProfile(); // Initialize with test data
		UserProfileDTO userProfileDTO = new UserProfileDTO(); // Initialize with test data

		when(userService.addUserProfile(any(UserProfileDTO.class))).thenReturn(userProfileDTO);

		mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userProfile))).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(userProfileDTO.getFirstName())); // Adjust according to your
																							// DTO fields
	}

	@Test
	void getUserProfile_ShouldReturnUserProfile() throws Exception {
		Long profileId = 1L;
		UserProfileDTO userProfileDTO = new UserProfileDTO(); // Initialize with test data

		when(userService.getUserProfile(profileId)).thenReturn(userProfileDTO);

		mockMvc.perform(get("/api/users/{profileId}", profileId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(userProfileDTO.getFirstName())); // Adjust according to your
																							// DTO fields
	}

	@Test
	void getUserProfileByMobile_ShouldReturnUserProfile() throws Exception {
		String mobileNumber = "1234567890";
		UserProfileDTO userProfileDTO = new UserProfileDTO(); // Initialize with test data

		when(userService.getUserProfileByMobile(mobileNumber)).thenReturn(userProfileDTO);

		mockMvc.perform(get("/api/users/mobile/{mobileNumber}", mobileNumber)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(userProfileDTO.getFirstName())); // Adjust according to your
																							// DTO fields
	}

	@Test
	void getUserProfileByName_ShouldReturnUserProfile() throws Exception {
		String firstName = "John";
		UserProfileDTO userProfileDTO = new UserProfileDTO(); // Initialize with test data

		when(userService.getUserProfileByName(firstName)).thenReturn(userProfileDTO);

		mockMvc.perform(get("/api/users/name").param("firstName", firstName)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(userProfileDTO.getFirstName())); // Adjust according to your
																							// DTO fields
	}

	@Test
	void deleteUserProfile_ShouldReturnNoContent() throws Exception {
		Long profileId = 1L;

		doNothing().when(userService).deleteUserProfile(profileId);

		mockMvc.perform(delete("/api/users/{profileId}", profileId)).andExpect(status().isNoContent());

		verify(userService, times(1)).deleteUserProfile(profileId);
	}

}
