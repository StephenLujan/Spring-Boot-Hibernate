package com.catalystdevworks.slujan.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalystdevworks.slujan.controller.UserController;
import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.service.UserService;
import com.catalystdevworks.slujan.util.UserUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest
{

	@Mock
	private UserService userService;

	private UserController userController;

	@Before
	public void setUp() throws Exception
	{
		userController = new UserController(userService);
	}

	@Test
	public void shouldCreateUser() throws Exception
	{
		final User savedUser = stubServiceToReturnStoredUser();
		final User user = UserUtil.createUser();
		User returnedUser = userController.createUser(user);
		// verify user was passed to UserService
		verify(userService, times(1)).create(user);
		assertEquals("Returned user should come from the service", savedUser,
				returnedUser);
	}

	private User stubServiceToReturnStoredUser()
	{
		final User user = UserUtil.createUser();
		when(userService.create(any(User.class))).thenReturn(user);
		return user;
	}

	@Test
	public void shouldListAllUsers() throws Exception
	{
		stubServiceToReturnExistingUsers(10);
		Collection<User> users = userController.listUsers();
		assertNotNull(users);
		assertEquals(10, users.size());
		// verify user was passed to UserService
		verify(userService, times(1)).getAll();
	}

	private void stubServiceToReturnExistingUsers(int howMany)
	{
		when(userService.getAll())
				.thenReturn(UserUtil.createUserList(howMany));
	}

}
