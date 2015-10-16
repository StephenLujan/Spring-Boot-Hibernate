package com.catalystdevworks.slujan.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.catalystdevworks.slujan.domain.RoleEnum;
import com.catalystdevworks.slujan.domain.User;
import com.catalystdevworks.slujan.domain.UserRole;
import com.catalystdevworks.slujan.repository.UserRepository;

/**
 * This Configuration object just runs a command which will seed the
 * database with some test data. Just remove the @Configuration annotation
 * to disable the test data seeding.
 */
@Configuration
public class DatabaseTestDataPopulator
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DatabaseTestDataPopulator.class);
	
	@Autowired
	PasswordEncoder encoder;

	@Bean
	CommandLineRunner init(final UserRepository userRepository)
	{
		return new CommandLineRunner()
		{
			@Override
			public void run(String... arg0) throws Exception
			{
				LOGGER.info("populating database with test data...");

				User user = User
						.createUser("slujan", "slujan@catalystitservices.com",
								encoder.encode("pass"));
				UserRole admin = new UserRole(RoleEnum.ADMIN, user);
				user.getRoles().add(admin);
				userRepository.save(user);

				userRepository.save(User.createUser("pacman",
						"pacman@atari.com", encoder.encode("wakawakawaka")));
				userRepository.save(User.createUser("user", "a@b.com",
						encoder.encode("pass")));
			}
		};
	}
}
