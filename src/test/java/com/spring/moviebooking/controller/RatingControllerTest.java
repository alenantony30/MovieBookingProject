package com.spring.moviebooking.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.spring.moviebooking.dto.RatingsDTO;
import com.spring.moviebooking.entity.Ratings;
import com.spring.moviebooking.entity.Shows;

class RatingControllerTest {

	@Autowired
	RestTemplate restTemplate;
	
	@Test
	void testRateMovie() {
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		this.restTemplate = restTemplateBuilder.basicAuthentication("arun", "pass").build();

		
		RatingsDTO rating =new RatingsDTO(1,6.6,"Test rating");
		Ratings actual = restTemplate.postForObject("http://localhost:8585/movies/ratemovie", rating, Ratings.class);

		Assertions.assertNotNull(actual);

	}

	@Test
	void testGetRatingsByMovie() {
		
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		this.restTemplate = restTemplateBuilder.basicAuthentication("arun", "pass").build();

		List<Ratings> showsList = restTemplate.getForObject("http://localhost:8585/movies/getratingsbymovie/avatar", List.class);
		Assertions.assertNotEquals(0,showsList.size());
		List<Shows> showsList2 = restTemplate.getForObject("http://localhost:8585/movies/getratingsbymovie/avatarNot", List.class);
		Assertions.assertEquals(0,showsList2.size());
		
	}

}
