package com.catalystdevworks.slujan.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.domain.UserRole;
import com.catalystdevworks.slujan.repository.UserRepository;

public class MyUserDetailService implements UserDetailsService
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		User user = userRepository.findByUsername(username);
		if (user != null)
		{
			LOGGER.debug("Creating UserDetails for '" + username + "'");
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), true, true, true,
					true, MyUserDetailService.getAuthorities(user.getRoles()));
		} else
		{
			LOGGER.debug("could not find the user '" + username + "'");
			throw new UsernameNotFoundException("could not find the user '"
					+ username + "'");
		}
	}

	/**
	 * @param roles
	 *            is any Set of UserRole objects such as a User may possess
	 * @return a List of GrantedAuthorities usable by Spring Security
	 */
	public static List<GrantedAuthority> getAuthorities(Set<UserRole> roles)
	{
		// magic the list of UserRoles into a String array
		String[] strings = roles.stream().map(role -> role.getRoleName())
				.toArray(String[]::new);
		LOGGER.debug("user had roles: " + strings.toString());

		// the built in AuthorityUtils should be used to create
		// GrantedAuthorities
		return AuthorityUtils.createAuthorityList(strings);
	}

	public UserRepository getUserRepository()
	{
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
}
