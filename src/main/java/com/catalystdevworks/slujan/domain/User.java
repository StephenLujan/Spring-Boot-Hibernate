package com.catalystdevworks.slujan.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * An application user with all describing information and their credentials.
 */
@Entity
@Table(name = "users")
public class User
{

	@Id
	@NotNull
	@Size(max = 64)
	@Column(name = "username", unique = true)
	private String username;

	@NotNull
	@Size(max = 64)
	@Column(name = "password")
	private String password;

	@NotNull
	@Size(max = 64)
	@Column(name = "email", unique = true)
	private String email;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade =
	{ CascadeType.ALL })
	private Set<UserRole> roles;

	User()
	{
	}

	/**
	 * Creates a user with the input username, email, and password
	 * 
	 * @param username
	 * @param email
	 * @param password
	 * @return
	 */
	public static User createUser(String username, String email, String password)
	{
		User user = new User();

		user.username = username;
		user.email = email;
		user.password = password;

		if (user.roles == null)
		{
			user.roles = new HashSet<UserRole>();
		}

		// create a new user with basic user privileges
		user.roles.add(new UserRole(RoleEnum.USER, user));

		return user;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Set<UserRole> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<UserRole> roles)
	{
		this.roles = roles;
	}
}
