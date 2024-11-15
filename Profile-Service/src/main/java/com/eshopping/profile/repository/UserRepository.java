package com.eshopping.profile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.profile.entity.UserProfile;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
	Optional<UserProfile> findByMobileNumber(String mobileNumber);

	Optional<UserProfile> findByFirstName(String firstName);
}
