package com.example.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.UserPrincipal;

@Service
public class SomeService {
	public String getPublicMessage() {
		return "public";
	}

	@PreAuthorize("authenticated")
	public String getProtectedMessage() {
		Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (p instanceof UserPrincipal) {
			UserPrincipal up = (UserPrincipal) p;
			return "protected " + up.getUsername();
		}

		return "protected";
	}
}
