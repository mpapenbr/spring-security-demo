package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.DemoUsers;
import com.example.demo.model.User;

//@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private DemoUsers demoUsers;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> found = demoUsers.getUsers().stream().filter(u -> u.getName().equals(username)).findFirst();
		return found.map(u -> UserPrincipal.create(u)).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
