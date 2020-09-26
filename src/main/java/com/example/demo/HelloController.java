package com.example.demo;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/private/hello")
	public String sayPrivateHello(Principal principal) {
		return "Hello " + principal.getName();
	}
	
	@GetMapping(value = "/public/hello")
	public String sayPublicHello() {
		return "Hello stranger" ;
	}

}
