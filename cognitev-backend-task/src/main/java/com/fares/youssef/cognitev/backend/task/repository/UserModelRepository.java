package com.fares.youssef.cognitev.backend.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fares.youssef.cognitev.backend.task.model.Users;

public interface UserModelRepository extends JpaRepository<Users, String> {

//	@Query(value = "SELECT u FROM Users u where u.phone_number = ?1 and u.password = ?2 ")
	Optional<Users> findAllByPhoneNumberAndPassword(String username, String password);

	Optional<Users> findByToken(String token);

	Optional<Users> findById(Long id);

}
