package com.example.electionProject.services;

import org.springframework.stereotype.Service;

import com.example.electionProject.entites.User;
import com.example.electionProject.exception.DuplicateEmailException;
import com.example.electionProject.exception.DuplicatePhoneException;
import com.example.electionProject.exception.DuplicateEmailException;
import com.example.electionProject.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User createUser(User user) {
		return repository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User updateUser(Long id, User user) {
		user.setUserID(id);
		return repository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}

	@Override
	public User registerUser(User user) {
		
		if (repository.findByEmail(user.getEmail()).isPresent()) {
			throw new DuplicateEmailException("Email is already registered.");
		}
		
		if (repository.findByPhone(user.getPhone()).isPresent()) {
			throw new DuplicatePhoneException("Phone number is already registered.");
		}
		return repository.save(user);
	}
}