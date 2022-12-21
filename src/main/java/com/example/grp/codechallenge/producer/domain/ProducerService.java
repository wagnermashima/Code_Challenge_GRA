package com.example.grp.codechallenge.producer.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.grp.codechallenge.producer.repository.ProducerRepository;

@Controller
public class ProducerService {
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Transactional
	public Producer save(Producer producer) {
		return producerRepository.save(producer);
	}

	public List<Producer> getOrCreateProducers(List<String> producers) {
		return producers.stream().map(p -> getOrCreate(p)).collect(Collectors.toList());
	}

	private Producer getOrCreate(String name) {
		Producer producer = producerRepository.findByName(name);
		if (producer != null) return producer;
		
		Producer newProducer = new Producer();
		newProducer.setName(name);
		return save(newProducer);
	}

	public Long countProducers() {
		return producerRepository.count();
	}

	public ProducerResult getAwards() {
		ProducerResult result = new ProducerResult();
		result.setMin(getMinimumPrizeInterval());
		result.setMin(getMaximumPrizeInterval());
		return result;
	}

	private List<PrizeInterval> getMaximumPrizeInterval() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<PrizeInterval> getMinimumPrizeInterval() {
		// TODO Auto-generated method stub
		return null;
	}

}
