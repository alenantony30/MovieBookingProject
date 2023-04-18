package com.spring.moviebooking.service;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
class BookingsServiceTest {

	@Autowired
	BookingsService bookingsService;

	@Test
	void testGetAll() {

		assertNotNull(bookingsService.getAll());
	}



}
