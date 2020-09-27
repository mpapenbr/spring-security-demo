package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.service.advanced.WithMyOwnStuff;

@SpringBootTest
public class TestServiceAccessAdvanced {

	@Autowired
	private SomeService someService;

	@WithMyOwnStuff(username = "advanced-user-stuff")
	@Test
	void testName() throws Exception {
		assertEquals("protected advanced-user-stuff", someService.getProtectedMessage());
	}
}
