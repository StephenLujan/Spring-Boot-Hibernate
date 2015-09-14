package com.catalystdevworks.slujan.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.catalystdevworks.slujan.repository.UserRepository;

/**
 * This is a reference application for combining Hibernate ORM, REST, and Spring
 * Security on a Spring Boot Application
 */
// Main annotation for Spring Boot
@SpringBootApplication
// Tell spring boot where to look for components to @Autowire
@ComponentScan(
{ "com.catalystdevworks.slujan" })
// Tell Hibernate ORM (now part of Spring Boot) where to look for entities
@EntityScan("com.catalystdevworks.slujan.domain")
// Finds Jpa Repository interfaces and implement them
@EnableJpaRepositories("com.catalystdevworks.slujan.repository")
// Allows the @Transactional annotation
@EnableTransactionManagement
public class HibernateSpringSecurityApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(HibernateSpringSecurityApplication.class, args);
	}

	// Look away now...
	@Bean
	CommandLineRunner init(final UserRepository userRepository)
	{
		return new CommandLineRunner()
		{
			@Override
			public void run(String... arg0) throws Exception
			{
				userRepository.save(com.catalystdevworks.slujan.domain.User
						.createUser("slujan", "slujan@catalystitservices.com",
								"pass"));
			}
		};
	}
}
