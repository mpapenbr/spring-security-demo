package com.example.demo.web;

import com.example.demo.SomeServiceController;
import com.example.demo.service.SomeService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/*
 * This class tests one controller only. Note, it can be combined with security out of the box.
 * @author mpapenbr
 *
 */
@WebMvcTest(controllers = SomeServiceController.class)
public class TestControllerWithWebMvcTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SomeService someService;

	@Test
	void testPublic() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/public/some")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testProtectedNoUser() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/private/some")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@WithAnonymousUser
	@Test
	void testProtectedAnonymous() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/private/some")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@WithMockUser
	@Test
	void testProtectedWithSomeMockUser() throws Exception {
		Mockito.when(someService.getProtectedMessage()).thenReturn("Hello Mocki");
		mockMvc.perform(MockMvcRequestBuilders.get("/private/some")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello Mocki"));
	}

}
