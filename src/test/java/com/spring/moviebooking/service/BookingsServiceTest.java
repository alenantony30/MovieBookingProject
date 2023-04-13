package com.spring.moviebooking.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;

class BookingsServiceTest {

	

	@Autowired
	RestTemplate restTemplate;


	@Test
	void testBookTicket() {
		RestTemplateBuilder restTemplateBuilder=new RestTemplateBuilder();
		this.restTemplate = restTemplateBuilder
	            .basicAuthentication("arun", "pass")
	            .build();
		BookingsDTO rating =new BookingsDTO(1,"CC",1);
		Bookings actual = restTemplate.postForObject("http://localhost:8585/movies/bookticket", rating, Bookings.class);
		
		Assertions.assertNotNull(actual);
		
		List<Bookings> bookingsList = restTemplate.getForObject("http://localhost:8585/movies/getmybookinghistory", List.class);
		Assertions.assertNotEquals(0,bookingsList.size());
		int id=actual.getBookingId();
		restTemplate.delete("http://localhost:8585/movies/cancelbooking/"+id );
	}

	

}
