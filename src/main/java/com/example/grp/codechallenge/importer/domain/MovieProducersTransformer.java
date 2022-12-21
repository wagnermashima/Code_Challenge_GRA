package com.example.grp.codechallenge.importer.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.example.grp.codechallenge.movie.domain.MovieProducers;

/**
 * @see MovieProducersTransformerTest
 * @author wagnermashima
 */

public class MovieProducersTransformer {

	public MovieProducers transform(String txFile) {
		if (!isValid(txFile)) {
			throw new RuntimeException(String.format("File incomplete line = [%s]", txFile));
		}
		String[] split = txFile.split(";");
		MovieProducers movie = new MovieProducers();
		movie.setYear(extractYear(split));
		movie.setTitle(extractTitle(split));
		movie.setStudios(extractStudios(split));
		movie.setProducers(extractProducers(split));
		movie.setWinner(extractWinner(split));
		return movie;
	}

	private boolean isValid(String txFile) {
		if (!StringUtils.hasText(txFile)) return false;
		if (txFile.split(";").length < 4) return false;
		return true;
	}

	private Boolean extractWinner(String[] split) {
		if (split.length == 4) return false;
		String winner = split[4];
		if (!StringUtils.hasText(winner)) return false;
		return "YES".equals(winner.trim().toUpperCase());
	}

	private List<String> extractProducers(String[] split) {
		String[] producers = split[3].split(",| and ");
		List<String> result = new ArrayList<>();
		for (int i = 0; i < producers.length; i++) {
			String name = producers[i].trim();
			if (StringUtils.hasText(name)) {
				result.add(name);
			}
		}
		return result;
	}

	private String extractStudios(String[] split) {
		return split[2];
	}

	private String extractTitle(String[] split) {
		return split[1];
	}

	private Integer extractYear(String[] split) {
		return Integer.valueOf(split[0]);
	}

}
