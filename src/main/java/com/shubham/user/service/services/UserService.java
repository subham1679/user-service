package com.shubham.user.service.services;

import java.util.List;

import com.shubham.user.service.entities.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	//delete
	//update

}
