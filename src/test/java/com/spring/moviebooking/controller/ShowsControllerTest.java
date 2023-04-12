package com.spring.moviebooking.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.spring.moviebooking.entity.Shows;

class ShowsControllerTest {

	@Autowired
	RestTemplate restTemplate;

	@Test
	void testGetShowsByMovies() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		this.restTemplate = restTemplateBuilder.basicAuthentication("arun", "pass").build();

		List<Shows> showsList = restTemplate.getForObject("http://localhost:8585/movies/getshows/avatar", List.class);
		Assertions.assertNotEquals(0,showsList.size());
		List<Shows> showsList2 = restTemplate.getForObject("http://localhost:8585/movies/getshows/avatarnot", List.class);
		Assertions.assertEquals(0,showsList2.size());
	}

}
