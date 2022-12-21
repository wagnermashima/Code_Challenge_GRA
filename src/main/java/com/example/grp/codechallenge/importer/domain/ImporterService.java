package com.example.grp.codechallenge.importer.domain;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.grp.codechallenge.movie.domain.MovieService;

@Controller
public class ImporterService {
	
	@Autowired
	private FileReader fileReader;
	
	@Autowired
	private MovieService movieService;
	
	@PostConstruct
	@Transactional
	public void importMovies() {
		List<String> lines = fileReader.readFile();
		MovieProducersTransformer transformer = new MovieProducersTransformer();
		lines.stream().map(l -> transformer.transform(l)).forEach(m -> movieService.importMoviesProducers(m));
	}
}
