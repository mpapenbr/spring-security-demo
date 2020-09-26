package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

/*
 *
 * This test used the complete container (including web and whatever else stuff is configured)
 *
 */
@SpringBootTest
public class TestServiceAccess {

	@Autowired
	private SomeService someService;

	@Test
	void testPublic() throws Exception {
		assertEquals("public", someService.getPublicMessage());
	}

	@Test
	void testProtectedNoUser() throws Exception {
		assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
			someService.getProtectedMessage();
		});
	}

	@WithAnonymousUser
	@Test
	void testProtectedAnonymous() throws Exception {
		assertThrows(AccessDeniedException.class, () -> {
			someService.getProtectedMessage();
		});
	}

	@WithMockUser
	@Test
	void testProtectedWithSomeMockUser() throws Exception {
		assertEquals("protected", someService.getProtectedMessage());
	}

	/*
	 * Note: This will use our own provided users (via application.yml)
	 * We don't need to provide a password but notice the additional name in the message that is
	 * returned.
	 * Additional notes:
	 * when using @WithUserDetails the user is required to exist!
	 *
	 * @WithUserDetails(value="invalid") would result in test setup exception
	 *
	 * @WithUserDetails would also throw an exception since in our setup there is no user "user"
	 *
	 */
	@WithUserDetails(value = "mp")
	@Test
	void testProtectedWithOwnUser() throws Exception {
		assertEquals("protected mp", someService.getProtectedMessage());
	}

}
