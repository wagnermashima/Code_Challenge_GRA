package com.example.grp.codechallenge.producer.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerRestController {
	
	@Autowired
	private ProducerService producerService;
	
	@GetMapping("/awards")
	public ProducerResult get() {
		return producerService.getAwards();
	}

}
