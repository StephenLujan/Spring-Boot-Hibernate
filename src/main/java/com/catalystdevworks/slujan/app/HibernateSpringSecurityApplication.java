package com.catalystdevworks.slujan.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.catalystdevworks.slujan.domain.RoleEnum;
import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.domain.UserRole;
import com.catalystdevworks.slujan.repository.UserRepository;

/**
 * This is a reference application for combining Hibernate ORM, REST, and Spring
 * Security on a Spring Boot Application
 */
// Main annotation for Spring Boot
@SpringBootApplication
// Tell spring boot where to look for components to @Autowire
@ComponentScan("com.catalystdevworks.slujan")
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
}
