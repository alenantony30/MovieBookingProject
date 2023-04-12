package com.spring.moviebooking.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.spring.moviebooking.entity.Theatres;

class TheatresControllerTest {

	@Autowired
	RestTemplate restTemplate;

	@Test
	void testSearchTheatres() {
		
		RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
		this.restTemplate = restTemplateBuilder
	            .basicAuthentication("arun", "pass")
	            .build();
		List<Theatres> theatresList = restTemplate.getForObject("http://localhost:8585/movies/searchTheatres/dubai", List.class);
		Assertions.assertNotEquals(0,theatresList.size());
		List<Theatres> theatresList2 = restTemplate.getForObject("http://localhost:8585/movies/searchTheatres/notdubai", List.class);
		Assertions.assertEquals(0,theatresList2.size());
	}

}
