package com.example.grp.codechallenge.importer.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.grp.codechallenge.movie.domain.MovieProducers;

class MovieProducersTransformerTest {
	
	private MovieProducersTransformer movieTransformer;
	
	@BeforeEach
	void setup() {
		movieTransformer = new MovieProducersTransformer();
	}

	@Test
	void transform_movie() {
		MovieProducers movie = movieTransformer.transform("1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;yes");
		
		assertEquals(1980, movie.getYear());
		assertEquals("Can't Stop the Music", movie.getTitle());
		assertEquals("Associated Film Distribution", movie.getStudios());
		assertEquals("Allan Carr", movie.getProducers().get(0));
		assertTrue(movie.getWinner());
	}
	
	@Test
	void transform_movie_not_winner() {
		MovieProducers movie = movieTransformer.transform("1980;Can't Stop the Music;Associated Film Distribution;Allan Carr;");
		
		assertEquals(1980, movie.getYear());
		assertEquals("Can't Stop the Music", movie.getTitle());
		assertEquals("Associated Film Distribution", movie.getStudios());
		assertEquals("Allan Carr", movie.getProducers().get(0));
		assertFalse(movie.getWinner());
	}
	
	@Test
	void text_null() {
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> movieTransformer.transform(null));
		
		assertEquals("File incomplete line = [null]", exception.getMessage());
	}
	
	@Test
	void text_empty() {
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> movieTransformer.transform(""));
		
		assertEquals("File incomplete line = []", exception.getMessage());
	}
	
	@Test
	void text_blank() {
		RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> movieTransformer.transform(" "));
		
		assertEquals("File incomplete line = [ ]", exception.getMessage());
	}
	
	@Test
	void transform_movie_multiple_producers() {
		MovieProducers movie = movieTransformer.transform("1996;Barb Wire;PolyGram Filmed Entertainment, Gramercy Pictures;Todd Moyer, Mike Richardson and Brad Wyman;");
		
		assertEquals(1996, movie.getYear());
		assertEquals("Barb Wire", movie.getTitle());
		assertEquals("PolyGram Filmed Entertainment, Gramercy Pictures", movie.getStudios());
		assertEquals("Todd Moyer", movie.getProducers().get(0));
		assertEquals("Mike Richardson", movie.getProducers().get(1));
		assertEquals("Brad Wyman", movie.getProducers().get(2));
		assertFalse(movie.getWinner());
	}
	
	@Test
	void no_name_producer() {
		MovieProducers movie = movieTransformer.transform("2019;Cats;Universal Pictures;Debra Hayward, Tim Bevan, Eric Fellner, and Tom Hooper;yes");
		
		assertEquals("Debra Hayward", movie.getProducers().get(0));
		assertEquals("Tim Bevan", movie.getProducers().get(1));
		assertEquals("Eric Fellner", movie.getProducers().get(2));
		assertEquals("Tom Hooper", movie.getProducers().get(3));
	}

}
