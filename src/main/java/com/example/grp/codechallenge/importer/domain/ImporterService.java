package com.example.grp.codechallenge.importer.domain;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.grp.codechallenge.movie.domain.MovieService;
import com.example.grp.codechallenge.producer.domain.ProducerService;

@Component
public class ImporterService {
	
	@Autowired
	private FileReader fileReader;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ProducerService producerService;
	
	@PostConstruct
	public void importMovies() {
		movieService.clear();
		producerService.clear();
		List<String> lines = fileReader.readFile();
		MovieProducersTransformer transformer = new MovieProducersTransformer();
		lines.stream().map(l -> transformer.transform(l)).forEach(m -> movieService.importMoviesProducers(m));
	}
}
