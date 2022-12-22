package com.example.grp.codechallenge.producer.domain;

import java.util.ArrayList;
import java.util.Iterator;
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
		List<ProducerWinner> winners = producerRepository.findWinnersMoreThanOnce();
		List<PrizeInterval> prizeIntervals = createPrizeIntervals(winners);
		result.setMin(filter(getMinimumInterval(prizeIntervals), prizeIntervals));
		result.setMax(filter(getMaximumInterval(prizeIntervals), prizeIntervals));
		return result;
	}

	private List<PrizeInterval> createPrizeIntervals(List<ProducerWinner> winners) {
		List<PrizeInterval> result = new ArrayList<>();
		for (Iterator<ProducerWinner> iterator = winners.iterator(); iterator.hasNext();) {
			ProducerWinner producerWinner = iterator.next();
			
			ProducerWinner other = winners.stream().filter(w -> shouldApply(producerWinner, w)).findFirst().orElse(null);
			if (other == null) continue;
			
			PrizeInterval prizeInterval = new PrizeInterval();
			prizeInterval.setPreviousWin(producerWinner.getYear());
			prizeInterval.setFollowingWin(other.getYear());
			prizeInterval.setProducer(producerWinner.getName());
			prizeInterval.setInterval(other.getYear() - producerWinner.getYear());
			
			result.add(prizeInterval);
			
			iterator.remove();
		}
		return result;
	}

	private boolean shouldApply(ProducerWinner base, ProducerWinner other) {
		if (!base.getName().equals(other.getName())) return false;
		return !base.getTitle().equals(other.getTitle());
	}

	private int getMaximumInterval(List<PrizeInterval> prizeIntervals) {
		return prizeIntervals.stream().mapToInt(PrizeInterval::getInterval).max().orElse(0);
	}

	private int getMinimumInterval(List<PrizeInterval> prizeIntervals) {
		return prizeIntervals.stream().mapToInt(PrizeInterval::getInterval).min().orElse(0);
	}
	
	private List<PrizeInterval> filter(int interval, List<PrizeInterval> prizeIntervals) {
		if (interval == 0) return new ArrayList<>();
		return prizeIntervals.stream().filter(i -> i.getInterval().equals(interval)).collect(Collectors.toList());
	}

}
