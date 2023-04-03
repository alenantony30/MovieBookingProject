package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;

public interface IBookingsService {
	
	public List<Bookings> getAll();
	
	public Bookings bookTicket(BookingsDTO bookings);
	
	

	

}
