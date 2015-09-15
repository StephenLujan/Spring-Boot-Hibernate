package com.catalystdevworks.slujan.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.catalystdevworks.slujan.domain.UserRole;
import com.catalystdevworks.slujan.repository.UserRepository;

public class MyUserDetailService implements UserDetailsService
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
			return new User(user.getUsername(), user.getPassword(), true, true,
					true, true, MyUserDetailService.getAuthorities(user
							.getRoles()));
		} 
		else 
		{
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

		// the built in AuthorityUtils should be used to create
		// GrantedAuthorities
		return AuthorityUtils.createAuthorityList(strings);
	}
}
