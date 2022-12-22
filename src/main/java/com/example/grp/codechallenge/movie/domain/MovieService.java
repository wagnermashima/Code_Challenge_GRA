package com.example.grp.codechallenge.movie.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.grp.codechallenge.movie.repository.MovieRepository;
import com.example.grp.codechallenge.producer.domain.Producer;
import com.example.grp.codechallenge.producer.domain.ProducerService;

@Controller
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ProducerService producerService;

	@Transactional
	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	public Long countMovies() {
		return movieRepository.count();
	}

	@Transactional
	public void importMoviesProducers(MovieProducers movieProducers) {
		List<Producer> producers = producerService.getOrCreateProducers(movieProducers.getProducers());
		
		Movie movie = new Movie();
		movie.setYear(movieProducers.getYear());
		movie.setTitle(movieProducers.getTitle());
		movie.setStudios(movieProducers.getStudios());
		movie.setWinner(movieProducers.getWinner());
		movie.setProducers(producers);
		
		save(movie);
	}

	@Transactional
	public void clear() {
		movieRepository.clear();
	}
}
