package com.catalystdevworks.slujan.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.repository.UserRepository;
import com.catalystdevworks.slujan.service.UserAlreadyExistsException;
import com.catalystdevworks.slujan.service.UserService;
import com.catalystdevworks.slujan.service.UserServiceImpl;
import com.catalystdevworks.slujan.util.UserUtil;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest
{

	@Mock
	private UserRepository userRepository;

	private UserService userService;

	@Before
	public void setUp() throws Exception
	{
		userService = new UserServiceImpl(userRepository);
	}

	@Test
	public void shouldSaveNewUser_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedUserShouldBeReturned()
			throws Exception
	{
		final User savedUser = stubRepositoryToReturnUserOnSave();
		final User user = UserUtil.createUser();
		final User returnedUser = userService.create(user);
		// verify repository was called with user
		verify(userRepository, times(1)).save(user);
		assertEquals("Returned user should come from the repository",
				savedUser, returnedUser);
	}

	private User stubRepositoryToReturnUserOnSave()
	{
		User user = UserUtil.createUser();
		when(userRepository.save(any(User.class))).thenReturn(user);
		return user;
	}

	@Test
	public void shouldSaveNewUser_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown()
			throws Exception
	{
		stubRepositoryToReturnExistingUser();
		try
		{
			userService.create(UserUtil.createUser());
			fail("Expected exception");
		} catch (UserAlreadyExistsException ignored)
		{
		}
		verify(userRepository, never()).save(any(User.class));
	}

	private void stubRepositoryToReturnExistingUser()
	{
		final User user = UserUtil.createUser();
		when(userRepository.findOne(user.getUsername())).thenReturn(user);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
	}

	@Test
	public void shouldListAllUsers_GivenThereExistSome_ThenTheCollectionShouldBeReturned()
			throws Exception
	{
		stubRepositoryToReturnExistingUsers(10);
		Collection<User> list = userService.getList();
		assertNotNull(list);
		assertEquals(10, list.size());
		verify(userRepository, times(1)).findAll();
	}

	private void stubRepositoryToReturnExistingUsers(int howMany)
	{
		when(userRepository.findAll()).thenReturn(
				UserUtil.createUserList(howMany));
	}

	@Test
	public void shouldListAllUsers_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned()
			throws Exception
	{
		stubRepositoryToReturnExistingUsers(0);
		Collection<User> list = userService.getList();
		assertNotNull(list);
		assertTrue(list.isEmpty());
		verify(userRepository, times(1)).findAll();
	}

	@Test
	public void shouldThrowUserNotFoundException_GivenEmailNotPresent()
	{
		stubRepositoryToReturnExistingUser();
		try
		{
			userService.getUserByEmail("missingEmail");
			fail("Expected exception");
		} catch (UserNotFoundException ignored)
		{
		}
	}
	
	@Test
	public void shouldThrowUserNotFoundException_GivenUsernameNotPresent()
	{
		stubRepositoryToReturnExistingUser();
		try
		{
			userService.getUserByUsername("missingUsername");
			fail("Expected exception");
		} catch (UserNotFoundException ignored)
		{
		}
	}
}
