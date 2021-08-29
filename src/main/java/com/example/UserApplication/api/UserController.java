package com.example.UserApplication.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.UserApplication.model.User;
import com.example.UserApplication.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public String registerUser(@Valid @RequestBody User user){
		try{
			return userService.registerUser(user);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
	}
	
	@GetMapping("/users")
	public List<User> getAll(){
		return userService.getAll();		
	}
	
	@GetMapping("/users/{uuid}")
	public User getUserById(@PathVariable("uuid") UUID id){
		User user = userService.findById(id); 
		return user;
	}
	
	@PostMapping("/users/{uuid}")
	public String updateUser(@RequestBody User newUser, @PathVariable("uuid") UUID id) {
		try{
			return	userService.updateUserById(newUser,id);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	 }	
	
}
