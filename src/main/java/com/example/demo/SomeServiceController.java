package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.SomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SomeServiceController {

	private final SomeService someService;

	@GetMapping(value = "/public/some")
	public String publicSomeCall() {
		return someService.getPublicMessage();
	}

	@GetMapping(value = "/private/some")
	public String privateSomeCall() {
		return someService.getProtectedMessage();
	}

}
