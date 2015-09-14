package com.catalystdevworks.slujan.domain;

//import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.ToString;

@Entity
@Table(name = "users")
@ToString
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
    @Version
    private Long version;

	@NotNull
	@Size(max = 64)
	@Column(name = "password")
	private String password;
	
	@NotNull
	@Size(max = 64)
	@Column(name = "username", unique = true)
    private String username;
	
	@NotNull
	@Size(max = 64)
	@Column(name = "email", unique = true)
    private String email;
	
	@OneToMany(mappedBy = "user")
    private Set<UserRole> roles;

	User() {
	}
	
    public static User createUser(String username, String email, String password) {
        User user = new User();

        user.username = username;
        user.email = email;
        user.password = password;

        if(user.roles == null) {
            user.roles = new HashSet<UserRole>();
        }

        //create a new user with basic user privileges
        user.roles.add(
                new UserRole(
                        RoleEnum.USER.toString(),
                        user
                ));

        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}

