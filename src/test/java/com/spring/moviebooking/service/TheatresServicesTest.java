package com.spring.moviebooking.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TheatresServicesTest {
	
	@Autowired
	TheatresServices theatreService;

	@Test
	void testGetTheatres() {
		assertFalse(theatreService.getTheatres().isEmpty());
	}

	@Test
	void testSearchTheatresByKeyword() {
		assertFalse(theatreService.searchTheatresByKeyword("dubai").isEmpty());
		assertTrue(theatreService.searchTheatresByKeyword("dubaidd").isEmpty());
	}

}
