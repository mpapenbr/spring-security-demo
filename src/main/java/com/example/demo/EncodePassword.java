package com.example.demo;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;



public class EncodePassword  {

	public static void main(String[] args) {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String enc = encoder.encode("abc");
		System.out.println("EncodePassword.run() " + enc);
		
	}

	
	
}
