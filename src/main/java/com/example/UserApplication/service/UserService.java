package com.example.UserApplication.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserApplication.dao.UserDao;
import com.example.UserApplication.model.User;

@Service
public class UserService implements UserDetailsService{
	
	private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User findById(UUID id){
		return  userDao.findById(id);
	}
	
	public String updateUserById(User newUser,UUID id){
		User user = findById(id);
		if(user!=null){
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user = userDao.updateUser(user);
			return  "Sucessfully Updated User with ID = "+user.getId().toString();
		}else{
			throw new IllegalStateException("user not found");
		}
	}
	
    public String registerUser(User user) {
        User userExists = getUserByUsername(user);
        if (userExists!=null) {
            throw new IllegalStateException("username already exist by Id : "+userExists.getId());
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setId(UUID.randomUUID());
        user = userDao.addUser(user);
        return  "Sucessfully Resgister with ID = "+user.getId().toString();
    }
	
	public User updateUser(User user){
		return userDao.updateUser(user);
	}
	
	public List<User> getAll(){
		return  userDao.getAllUser();
	}
	
	public User getUserByUsername(User user){
		List<User> userList =  userDao.getAllUser();
		if(!userList.isEmpty() || userList.size()>0){
			for(User other : userList){
				if(other.getUsername().equalsIgnoreCase(user.getUsername())){
					return other;
				}	
			}
		}	
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user  = userDao.findByUsername(username);
		if(user!=null){
			return user;
		}else{
			throw new UsernameNotFoundException(
                    String.format(USER_NOT_FOUND_MSG, username));
		}
	}
	
}
