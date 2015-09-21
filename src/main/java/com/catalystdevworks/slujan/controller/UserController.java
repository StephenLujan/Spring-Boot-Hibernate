package com.catalystdevworks.slujan.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.service.UserAlreadyExistsException;
import com.catalystdevworks.slujan.service.UserService;

import javax.validation.Valid;

import java.util.List;


@PreAuthorize("hasRole('USER')")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.create(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> listUsers() {
        LOGGER.debug("Received request to list all users");
        return userService.getList();
    }
    
	/**
	 * Get a specific user's details by username
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/user/{username}", method=RequestMethod.GET)
	public User getUser(@PathVariable String username){
		return userService.getUserByUserName(username);
	}
	
	/**
	 * Update the information on a user.
	 * @param username
	 * @param user
	 */
	@RequestMapping(value="/user/{username}", method=RequestMethod.PUT)
	public void  putUser(@PathVariable String username, @RequestBody User user){
		userService.createOrUpdate(user);
		System.err.println(username + " : " + user.toString()); //debug message
	}
    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }

}
