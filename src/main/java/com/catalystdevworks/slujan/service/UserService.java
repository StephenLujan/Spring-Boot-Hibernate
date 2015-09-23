package com.catalystdevworks.slujan.service;

import java.util.List;

import com.catalystdevworks.slujan.domain.User;


public interface UserService {

    User create(User user);

	User createOrUpdate(User user);
	
    List<User> getList();

	User getUserByUsername(String username);
	
	User getUserByEmail(String email);
	
}
