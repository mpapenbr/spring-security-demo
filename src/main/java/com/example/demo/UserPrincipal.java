package com.example.demo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails {

	private User currentUser;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(User user, Collection<? extends GrantedAuthority> authorities) {
		this.currentUser = user;
		this.authorities = authorities;
	}

	public static UserPrincipal create(User user) {
		return new UserPrincipal(user, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return currentUser.getPassword();
	}

	@Override
	public String getUsername() {
		return currentUser.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
