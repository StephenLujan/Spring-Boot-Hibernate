package com.catalystdevworks.slujan.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.catalystdevworks.slujan.app.HibernateSpringSecurityApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HibernateSpringSecurityApplication.class)
@WebAppConfiguration
public class HibernateSpringSecurityApplicationTests {

	@Test
	public void contextLoads() {
	}

}
