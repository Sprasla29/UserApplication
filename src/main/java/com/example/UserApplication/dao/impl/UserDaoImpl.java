package com.example.UserApplication.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.UserApplication.dao.UserDao;
import com.example.UserApplication.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	HashMap<UUID, User> userData = new HashMap<UUID, User>();
	
	@Override
	public User addUser(User user){
		userData.put(user.getId(), user);
		return userData.get(user.getId());
	}

	@Override
	public User updateUser(User user) {
		return userData.replace(user.getId(), user);
	}

	@Override
	public User findById(UUID id) {
		return userData.get(id);
	}

	@Override
	public List<User> getAllUser() {
		List<User> list = new ArrayList<User>(userData.values());
		return list;
	}
	
	@Override
	public User findByUsername(String username) {
		List<User> userList =  getAllUser();
		if(!userList.isEmpty() || userList.size()>0){
			for(User other : userList){
				if(other.getUsername().equalsIgnoreCase(username)){
					return other;
				}	
			}
		}
		return null;
	}
	
}
