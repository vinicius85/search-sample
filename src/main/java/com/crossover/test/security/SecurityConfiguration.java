package com.crossover.test.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security Configurations
 * 
 * @author vinicius
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	//Database credentials
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		//from datasource
		auth.jdbcAuthentication().dataSource(dataSource)
			//get users	
			.usersByUsernameQuery("select username,password, enabled from users where username=?")
			//get roles
			.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		//disable temporarily csrf filter because of login Method Not Allowed
		.csrf()
			.disable()
		//Only ADMIN users allowed create an article
		.authorizeRequests()
			.antMatchers("/backoffice/createArticle")
			.access("hasRole('ROLE_ADMIN')")
		//for other requests, permit
		.anyRequest()
			.permitAll()
			.and()
		//login path, and username/password fields
		.formLogin()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
		//also can logout
		.logout()
			.permitAll();
	}
}