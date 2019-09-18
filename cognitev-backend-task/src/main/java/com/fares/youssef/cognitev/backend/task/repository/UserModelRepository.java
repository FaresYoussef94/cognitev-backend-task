package com.fares.youssef.cognitev.backend.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fares.youssef.cognitev.backend.task.model.Users;

public interface UserModelRepository extends JpaRepository<Users, String> {

	Users findAllByPhoneNumberAndPassword(String username, String password);

	Users findByToken(String token);

	Users findById(Long id);

}
