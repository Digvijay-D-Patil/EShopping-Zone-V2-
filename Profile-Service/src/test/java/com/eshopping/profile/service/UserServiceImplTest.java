package com.eshopping.profile.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.eshopping.profile.converter.UserProfileConverter;
import com.eshopping.profile.dto.UserProfileDTO;
import com.eshopping.profile.entity.UserProfile;
import com.eshopping.profile.enumeration.Role;
import com.eshopping.profile.exception.UserProfileNotFoundException;
import com.eshopping.profile.repository.UserRepository;

public class UserServiceImplTest {

	@Mock
	private UserRepository profileRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Autowired
	private UserProfileDTO sampleUserProfileDTO;

	@Autowired
	private UserProfile sampleUserProfile;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		sampleUserProfileDTO = new UserProfileDTO();
		sampleUserProfileDTO.setProfileId(1L);
		sampleUserProfileDTO.setFirstName("John");
		sampleUserProfileDTO.setLastName("Doe");
		sampleUserProfileDTO.setAbout("Test user");
		sampleUserProfileDTO.setGender("Male");
		sampleUserProfileDTO.setMobileNumber("+1234567890");
		sampleUserProfileDTO.setEmail("john.doe@example.com");
		sampleUserProfileDTO.setDob(LocalDate.of(1990, 1, 1));
		sampleUserProfileDTO.setRole(Role.DELIVERY_AGENT);

		sampleUserProfile = UserProfileConverter.dtoToEntity(sampleUserProfileDTO);
	}

	@Test
	public void testAddUserProfile() {
		when(profileRepository.save(any(UserProfile.class))).thenReturn(sampleUserProfile);

		UserProfileDTO result = userService.addUserProfile(sampleUserProfileDTO);

		assertEquals(sampleUserProfileDTO.getProfileId(), result.getProfileId());
		assertEquals(sampleUserProfileDTO.getFirstName(), result.getFirstName());
		verify(profileRepository, times(1)).save(any(UserProfile.class));
	}

	@Test
	public void testGetUserProfile() {
		when(profileRepository.findById(1L)).thenReturn(Optional.of(sampleUserProfile));

		UserProfileDTO result = userService.getUserProfile(1L);

		assertEquals(sampleUserProfileDTO.getProfileId(), result.getProfileId());
		assertEquals(sampleUserProfileDTO.getFirstName(), result.getFirstName());
		verify(profileRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetUserProfile_NotFound() {
		when(profileRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(UserProfileNotFoundException.class, () -> userService.getUserProfile(1L));
		verify(profileRepository, times(1)).findById(1L);
	}

	@Test
	public void testGetUserProfileByMobile() {
		when(profileRepository.findByMobileNumber("+1234567890")).thenReturn(Optional.of(sampleUserProfile));

		UserProfileDTO result = userService.getUserProfileByMobile("+1234567890");

		assertEquals(sampleUserProfileDTO.getProfileId(), result.getProfileId());
		assertEquals(sampleUserProfileDTO.getFirstName(), result.getFirstName());
		verify(profileRepository, times(1)).findByMobileNumber("+1234567890");
	}

	@Test
	public void testGetUserProfileByMobile_NotFound() {
		when(profileRepository.findByMobileNumber("+1234567890")).thenReturn(Optional.empty());

		assertThrows(UserProfileNotFoundException.class, () -> userService.getUserProfileByMobile("+1234567890"));
		verify(profileRepository, times(1)).findByMobileNumber("+1234567890");
	}

	@Test
	public void testGetUserProfileByName() {
		when(profileRepository.findByFirstName("John")).thenReturn(Optional.of(sampleUserProfile));

		UserProfileDTO result = userService.getUserProfileByName("John");

		assertEquals(sampleUserProfileDTO.getProfileId(), result.getProfileId());
		assertEquals(sampleUserProfileDTO.getFirstName(), result.getFirstName());
		verify(profileRepository, times(1)).findByFirstName("John");
	}

	@Test
	public void testGetUserProfileByName_NotFound() {
		when(profileRepository.findByFirstName("John")).thenReturn(Optional.empty());

		assertThrows(UserProfileNotFoundException.class, () -> userService.getUserProfileByName("John"));
		verify(profileRepository, times(1)).findByFirstName("John");
	}

	@Test
	public void testDeleteUserProfile() {
		when(profileRepository.existsById(1L)).thenReturn(true);
		doNothing().when(profileRepository).deleteById(1L);

		userService.deleteUserProfile(1L);

		verify(profileRepository, times(1)).existsById(1L);
		verify(profileRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteUserProfile_NotFound() {
		when(profileRepository.existsById(1L)).thenReturn(false);

		assertThrows(UserProfileNotFoundException.class, () -> userService.deleteUserProfile(1L));
		verify(profileRepository, times(1)).existsById(1L);
		verify(profileRepository, times(0)).deleteById(1L);
	}
}
