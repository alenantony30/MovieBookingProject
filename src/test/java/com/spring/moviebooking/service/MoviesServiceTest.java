package com.spring.moviebooking.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MoviesServiceTest {
	
	@Autowired
	MoviesService movieService;

	@Test
	void testGetMovies() {
		assertNotNull(movieService.getMovies());
	}

	@Test
	void testSearchMoviesByKeyword() {
		assertNotNull(movieService.searchMoviesByKeyword("avatar"));
		assertTrue(movieService.searchMoviesByKeyword("avatar12233323").isEmpty());
		assertNotNull(movieService.searchMoviesByKeyword("fiction"));
		assertTrue(movieService.searchMoviesByKeyword("fiction22").isEmpty());
	}

}
