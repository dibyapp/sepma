package com.sepmafibre.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/secured")
		.access("permitAll")
		.antMatchers("/secured")
		.access("hasAnyRole('ADMIN')")
		.and()
		.formLogin()
		.and()
		.rememberMe()
		.and()
		.logout()
		.logoutSuccessUrl("/secured")
		.and()
		.exceptionHandling();
		
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("Ayesha")
				.password("hijabi")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

}
