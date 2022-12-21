package com.example.grp.codechallenge.producer.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ProducerRestControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
