package com.catalystdevworks.slujan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.repository.UserRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService
{

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(final UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	@Transactional
	public User create(@NotNull @Valid final User user)
	{
		LOGGER.debug("Creating {}", user);
		User existing = repository.findOne(user.getUsername());
		if (existing != null)
		{
			throw new UserAlreadyExistsException(String.format(
					"There already exists a user with the username=%s",
					user.getUsername()));
		}
		return repository.save(user);
	}

	@Override
	@Transactional
	public User createOrUpdate(@NotNull @Valid final User user)
	{
		LOGGER.debug("Putting {}", user);
		return repository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getList()
	{
		LOGGER.debug("Retrieving the list of all users");
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByUsername(String username)
	{
		LOGGER.debug("Retrieving the user with the username {}", username);
		User user = repository.findOne(username);
		LOGGER.trace("Repository retrieved the user {}", user);
		if (user == null)
		{
			throw new UserNotFoundException(String.format(
					"No User could be found with the username %s", username));
		}
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByEmail(String email)
	{
		LOGGER.debug("Retrieving the user with the email {}", email);
		User user = repository.findByEmail(email);
		LOGGER.trace("Repository retrieved the user {}", user);
		if (user == null)
		{
			throw new UserNotFoundException(String.format(
					"No User could be found with the email address %s", email));
		}
		return user;
	}
}
