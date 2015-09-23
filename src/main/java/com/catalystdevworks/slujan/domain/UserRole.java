package com.catalystdevworks.slujan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * A persistant, {@link User}-granted, authorization to the application. A User
 * may have many UserRoles authorizing different areas of the application.
 * 
 * @see RoleEnum
 */
@Entity
@Table(name = "user_roles",
// to prevent a duplicate authorization from being added a unique constraint
// must apply to the combination of role and User.
uniqueConstraints = @UniqueConstraint(columnNames =
{ "role", "user_username" }))
public class UserRole
{

	/*
	 * @NotNull is not used on the id here because an id won't exist until it
	 * reaches the database, and @NotNull validates at the database and sooner
	 * if "@Validates" is used
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@NotNull
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private RoleEnum role;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User user;

	public UserRole()
	{
	}

	public UserRole(RoleEnum roleName, User user)
	{
		this.role = roleName;
		this.user = user;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public RoleEnum getRoleName()
	{
		return role;
	}

	public void setRoleName(RoleEnum roleName)
	{
		this.role = roleName;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}