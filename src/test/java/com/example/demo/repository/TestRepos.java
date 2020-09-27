package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.model.DbUser;

@DataJpaTest()
@TestPropertySource(properties = {
		"spring.jpa.hibernate.ddl-auto=validate",
		"spring.liquibase.enabled=true"
})
public class TestRepos {

	@Autowired
	private DbUserRepository userRepo;

	@Test
	void databaseHasBeenInitialized() throws Exception {

	}

	@Test
	void createDbUser() throws Exception {
		DbUser dbuser = DbUser.builder()
				.name("dbMp")
				.password("{noop}dbpwd")
				.build();
		userRepo.save(dbuser);
	}
}
