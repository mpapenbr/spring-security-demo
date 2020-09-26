package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.example.demo.model.DemoUsers;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	
	
	@Bean
	@ConfigurationProperties(prefix = "demo")
	public DemoUsers demoUsers() {
		return new DemoUsers();
	}

	@Autowired
	private UserDetailsService customUserDetailsService;
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.userDetailsService(customUserDetailsService);
//        auth.inMemoryAuthentication()
//         .passwordEncoder(encoder)
//         .withUser("spring")
//         .password(encoder.encode("secret"))
//         .roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/private/**")
          .authenticated()
          .antMatchers("/public/**")
          .permitAll()
          .and()
          .httpBasic();
    }
}