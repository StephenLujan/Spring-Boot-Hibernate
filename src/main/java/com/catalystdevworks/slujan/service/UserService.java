package com.catalystdevworks.slujan.service;

import java.util.List;

import com.catalystdevworks.slujan.domain.User;

/**
 * Performs logical operations between {@link User} objects and the data layer.
 */
public interface UserService
{

	/**
	 * @param user
	 *            A valid {@link User} object, with a unique username and email.
	 * @return The same {@link User}
	 */
	User create(User user);

	/**
	 * Will update a pre-existing User if {@link User#getUsername()} matches an
	 * existing database user. Otherwise it will create a new user in the
	 * database.
	 * 
	 * @param user
	 *            A valid {@link User} object
	 * @return The same {@link User}
	 */
	User createOrUpdate(User user);

	/**
	 * @return A {@link List} of all {@link User}s.
	 */
	List<User> getAll();

	/**
	 * @param username
	 * @return The {@link User} uniquely identified by the username
	 * @throws UserNotFoundException
	 */
	User getUserByUsername(String username) throws UserNotFoundException;

	/**
	 * @param email
	 * @return The {@link User} uniquely identified by the email
	 * @throws UserNotFoundException
	 */
	User getUserByEmail(String email) throws UserNotFoundException;

}
