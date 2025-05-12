package com.example.electionProject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.electionProject.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	    Optional<User> findByNameOrEmail(String name, String email);
	    Optional<User> findByEmail(String email);
	    Optional<User> findByPhone(String phone);
}	

