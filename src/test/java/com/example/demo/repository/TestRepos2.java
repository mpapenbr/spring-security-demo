package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.model.User;

@DataJpaTest()
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=validate",
		"spring.liquibase.enabled=true"
})
public class TestRepos2 {

	@Autowired
	private UserRepository userRepo;

	@Test
	void databaseHasBeenInitialized() throws Exception {

	}

	@Test
	void createDbUser() throws Exception {
		User dbuser = User.builder()
				.name("dbMp")
				.password("{noop}dbpwd")
				.build();
		userRepo.save(dbuser);
		assertEquals(1L, userRepo.count());
	}

	@Test
	void createAnotherDbUser() throws Exception {
		User dbuser = User.builder()
				.name("dbMp2")
				.password("{noop}dbpwd")
				.build();
		userRepo.save(dbuser);
		assertEquals(1L, userRepo.count());
	}
}
