package com.spring.moviebooking.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.spring.moviebooking.entity.Movies;

class MoviesControllerTest {

	@Autowired
	RestTemplate restTemplate;
	
	@Test
	void testSearchMovies() {
		RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
		
		this.restTemplate = restTemplateBuilder
	            .basicAuthentication("arun", "pass")
	            .build();

		List<Movies> searchByTitle = restTemplate.getForObject("http://localhost:8585/movies/searchmovies/avat", List.class);
		List<Movies> searchByGenre = restTemplate.getForObject("http://localhost:8585/movies/searchmovies/fiction", List.class);
		Assertions.assertNotEquals(0,searchByTitle.size());
		Assertions.assertNotEquals(0,searchByGenre.size());
		
	}

}
