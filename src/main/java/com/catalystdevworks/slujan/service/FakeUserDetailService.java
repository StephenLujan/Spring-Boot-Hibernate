package com.catalystdevworks.slujan.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.repository.UserRepository;

public class FakeUserDetailService implements UserDetailsService
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDetailsService.class);
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		LOGGER.debug(username);
		User user = userRepository.findOne(username);
		if (user != null)
		{
			LOGGER.debug("Creating UserDetails for '" + username + "'");
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), true, true, true,
					true, AuthorityUtils.createAuthorityList("USER"));
		} else
		{
			LOGGER.debug("could not find the user '" + username + "'");
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}
}
