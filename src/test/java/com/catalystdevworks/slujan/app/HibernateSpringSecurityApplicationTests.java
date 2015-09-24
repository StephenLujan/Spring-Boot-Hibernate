package com.catalystdevworks.slujan.app;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.catalystdevworks.slujan.app.HibernateSpringSecurityApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HibernateSpringSecurityApplication.class)
@WebAppConfiguration
//@ActiveProfiles("integration-test")
// Separate profile for web tests to avoid clashing databases
public class HibernateSpringSecurityApplicationTests
{

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private UserRepository userRepository;

	private MockMvc mvc;

	@Before
	public void setUp()
	{
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context)
				.addFilters(springSecurityFilterChain).build();
	}

	@Test
	public void contextLoads()
	{
	}

	@Test
	public void testHome() throws Exception
	{
		this.mvc.perform(get("/")).andExpect(status().isFound())
				.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	public void testGetUser() throws Exception
	{
		this.mvc.perform(get("/destination")).andExpect(status().isFound())
				.andExpect(redirectedUrl("http://localhost/login"));
		this.mvc.perform(
				post("/login")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.sessionAttr("username", "slujan")
						.sessionAttr("password", "pass"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("http://localhost/destination"));
				//.andExpect(jsonPath("password", equalTo("wakawakawaka")));
	}

}
