package com.example.demo;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.demo.model.DemoUsers;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@ConfigurationProperties(prefix = "demo")
	public DemoUsers demoUsers() {
		return new DemoUsers();
	}

	//	@Autowired
	//	private UserDetailsService customUserDetailsService;

	@Bean
	public UserDetailsService users() {

		Collection<UserDetails> users = demoUsers().getUsers().stream().map(u -> UserPrincipal.create(u)).collect(Collectors.toList());

		return new InMemoryUserDetailsManager(users);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsManager uds = auth.inMemoryAuthentication().getUserDetailsService();
		// System.out.println("SecurityConfig.configure()" + uds);
		auth.userDetailsService(users());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
				.authorizeRequests(ar -> ar.antMatchers("/private/**").authenticated()
						.antMatchers("/public/**").permitAll())
				.httpBasic();
		// @formatter:on

	}
}