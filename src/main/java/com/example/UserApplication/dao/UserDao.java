package com.example.UserApplication.dao;

import java.util.List;
import java.util.UUID;

import com.example.UserApplication.model.User;


public interface UserDao {

	public User addUser(User user);
	public User updateUser(User user);
	public User findById(UUID id);
	public List<User> getAllUser();
	public User findByUsername(String username);
	
}
