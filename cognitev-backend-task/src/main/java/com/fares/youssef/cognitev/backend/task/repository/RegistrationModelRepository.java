package com.fares.youssef.cognitev.backend.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fares.youssef.cognitev.backend.task.model.RegistrationModel;

public interface RegistrationModelRepository extends JpaRepository<RegistrationModel, String> {
	
}
