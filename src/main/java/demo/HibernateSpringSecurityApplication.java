package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({ "controller", "demo", "domain", "repository", "service" })
@EntityScan("domain")
@EnableJpaRepositories("repository")
@EnableTransactionManagement
public class HibernateSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateSpringSecurityApplication.class, args);
	}
}
