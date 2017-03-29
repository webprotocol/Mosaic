package com.example.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * 인증
		 */
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("1234").roles("ADMIN")
//			.and()
//			.withUser("user").password("1234").roles("USER");
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * 권한
		 */
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/country/**").hasRole("ADMIN")
//				.antMatchers("/city/**").hasRole("ADMIN")
				.antMatchers("/city/register").hasRole("ADMIN")
				.antMatchers("/city/modify/**").hasRole("ADMIN")
				.antMatchers("/city/unregister/**").hasRole("ADMIN")
			.and()
			.logout()
				.logoutSuccessUrl("/")
			.and()
			.formLogin()
				.loginPage("/login")
			.permitAll();
		
	}

}
