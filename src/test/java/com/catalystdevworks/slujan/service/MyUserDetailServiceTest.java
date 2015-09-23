package com.catalystdevworks.slujan.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.repository.UserRepository;
import com.catalystdevworks.slujan.util.UserUtil;

@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailServiceTest
{
	@Mock
	UserRepository userRepository;

	MyUserDetailService target;

	@Before
	public void setUp() throws Exception
	{
		target = new MyUserDetailService();
		target.setUserRepository(userRepository);
	}

	@Test
	public void shouldThrowUsernameNotFoundException()
	{
		String username = "missingUsername";
		try
		{
			target.loadUserByUsername("missingUsername");
			fail("Expected exception");
		} catch (UsernameNotFoundException ignored)
		{
		}
		verify(userRepository, times(1)).findOne(username);
	}

	@Test
	public void shouldReturnSpringUserWithAuthoritiesPerRoles()
	{
		String username = "username";
		User user = User.createUser(username, "email", "password");
		assertTrue(user.getRoles().size() > 0);

		when(userRepository.findOne(username)).thenReturn(user);
		UserDetails userDetails = target.loadUserByUsername(username);

		verify(userRepository, times(1)).findOne(username);
		assertEquals(username, userDetails.getUsername());
		assertEquals(user.getRoles().size(), userDetails.getAuthorities()
				.size());
	}

}
