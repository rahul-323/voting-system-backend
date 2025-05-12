package com.example.electionProject.services;

import java.util.List;

import com.example.electionProject.entites.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User registerUser(User user);
}
