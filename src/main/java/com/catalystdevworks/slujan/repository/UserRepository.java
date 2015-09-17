package com.catalystdevworks.slujan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalystdevworks.slujan.domain.User;

/**
 * JpaRepository is automatically implemented!
 */
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
	
	//public User findBySilliestUsername();
}
