package com.catalystdevworks.slujan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalystdevworks.slujan.domain.User;

/**
 * JpaRepository is automatically implemented!
 */
public interface UserRepository extends JpaRepository<User, Long>
{
	/**
	 * 
	 * @param username
	 * @return the User with a username exactly matching the input
	 * @see <a
	 *      href="http://docs.spring.io/spring-data/jpa/docs/1.9.0.RELEASE//reference/html/#jpa.query-methods.query-creation">
	 *      Jpa Query Methods: Query creation</a>
	 */
	public User findByUsername(String username);

	// public User findBySilliestUsername();
}
