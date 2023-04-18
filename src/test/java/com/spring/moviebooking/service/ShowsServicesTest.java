package com.spring.moviebooking.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShowsServicesTest {

	@Autowired
	ShowsServices showsServices;

	@Test
	void testGetShows() {
		assertFalse(showsServices.getShows().isEmpty());
	}

	@Test
	void testSearchShowsByTitle() {
		assertFalse(showsServices.searchShowsByTitle("avatar").isEmpty());
		assertTrue(showsServices.searchShowsByTitle("dubaidd").isEmpty());
	}

}
