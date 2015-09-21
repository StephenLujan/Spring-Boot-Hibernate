package com.catalystdevworks.slujan.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * A persistant, {@link User}-granted, authorization to the application.
 * A User may have many UserRoles authorizing different areas of the application.
 * 
 * @see RoleEnum
 */
@Entity
@Table(name = "user_roles")
public class UserRole
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@NotNull
	@Column(name = "role_name")
	@Enumerated(EnumType.STRING)
	private RoleEnum roleName;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private User user;

	public UserRole(){}

	public UserRole(RoleEnum roleName, User user)
	{
		this.roleName = roleName;
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
		return roleName;
	}

	public void setRoleName(RoleEnum roleName)
	{
		this.roleName = roleName;
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