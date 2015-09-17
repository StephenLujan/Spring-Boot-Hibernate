package com.catalystdevworks.slujan.app;

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

import com.catalystdevworks.slujan.repository.UserRepository;
import com.catalystdevworks.slujan.service.MyUserDetailService;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter
{
	@Autowired
	UserRepository userRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService()
	{
		return new MyUserDetailService();
	}
}

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity( //securedEnabled = true,
prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.anyRequest()
			.fullyAuthenticated()
			.antMatchers( "/js/**", "/css/**", "/loginform.html",
					"/fonts/**").permitAll().and()
			.formLogin()
			.loginPage("/login").failureUrl("/login?error").usernameParameter("username").permitAll().and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll().and()
			.httpBasic().and().csrf().disable();
	}
}