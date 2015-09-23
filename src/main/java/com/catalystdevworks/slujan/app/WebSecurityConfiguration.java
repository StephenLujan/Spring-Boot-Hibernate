package com.catalystdevworks.slujan.app;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.catalystdevworks.slujan.repository.UserRepository;
import com.catalystdevworks.slujan.service.FakeUserDetailService;
import com.catalystdevworks.slujan.service.MyUserDetailService;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter
{
	@Autowired
	UserRepository userRepository;

	private static PasswordEncoder encoder;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				passwordEncoder());
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

	@Bean
	UserDetailsService userDetailsService()
	{
		return new MyUserDetailService();
		// return new FakeUserDetailService();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		if (encoder == null)
		{
			// encoder = new BCryptPasswordEncoder();
			encoder = new FakeEncoder();
		}
		return encoder;
	}
}

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity( // securedEnabled = true,
prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
				.antMatchers("/js/**", "/css/**", "/loginform.html",
						"/fonts/**").permitAll().anyRequest()
				.fullyAuthenticated();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/")
				.failureUrl("/login?error").usernameParameter("username")
				.passwordParameter("password").permitAll();
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
		http.httpBasic().and().csrf().disable();
	}
}