package com.example.grp.codechallenge.producer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

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
	void minimum() {
		ProducerResult result = testRestTemplate.getForObject("/awards", ProducerResult.class);
		
		List<PrizeInterval> minimum = result.getMin();
		
		assertFalse(minimum.isEmpty());
		PrizeInterval test = minimum.get(0);
		assertEquals(1, test.getInterval());
		
		test = minimum.get(1);
		assertEquals(1, test.getInterval());
	}
	
	@Test
	void maximum() {
		ProducerResult result = testRestTemplate.getForObject("/awards", ProducerResult.class);
		
		List<PrizeInterval> max = result.getMax();
		
		assertFalse(max.isEmpty());
		PrizeInterval test = max.get(0);
		assertEquals(14, test.getInterval());
	}

}
