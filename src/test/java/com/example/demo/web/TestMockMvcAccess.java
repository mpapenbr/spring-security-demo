package com.example.demo.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.service.advanced.WithMyOwnStuff;

@SpringBootTest
public class TestMockMvcAccess {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@Test
	void testPublic() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/public/hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello stranger"))
				.andReturn();
	}

	@Test
	void testProtectedNoUser() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/private/hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isUnauthorized())
				.andReturn();
	}

	@WithAnonymousUser
	@Test
	void testProtectedAnonymous() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/private/hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isUnauthorized())
				.andReturn();
	}

	@WithMockUser
	@Test
	void testProtectedWithSomeMockUser() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/private/hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello user"))
				.andReturn();

	}

	@WithMockUser("mp")
	@Test
	void testProtectedWithOwnUser() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/private/hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello mp"))
				.andReturn();

	}

	@WithMyOwnStuff(username = "advanced-user-stuff")
	@Test
	void testProtectedWithOwnAnnotation() throws Exception {
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/private/hello"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello advanced-user-stuff"))
				.andReturn();

	}
}
