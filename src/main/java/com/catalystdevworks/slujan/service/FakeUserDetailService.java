package com.catalystdevworks.slujan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.catalystdevworks.slujan.repository.UserRepository;

public class FakeUserDetailService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		com.catalystdevworks.slujan.domain.User user = userRepository
				.findByUsername(username);
		if (user != null)
		{
			return new User(user.getUsername(), user.getPassword(),
					true, true, true, true,
					AuthorityUtils.createAuthorityList("USER"));
		} else
		{
			throw new UsernameNotFoundException(
					"could not find the user '" + username + "'");
		}
	}
}
