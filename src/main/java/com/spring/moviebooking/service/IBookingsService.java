package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.exception.MovieException;

public interface IBookingsService {
	
	public List<Bookings> getAll();
	
	public Bookings bookTicket(BookingsDTO bookings) throws MovieException;
	
	
	public List<Bookings> getAllMyBookings();
	
	public String cancelMyBooking(int bookingId) throws MovieException;
	

}
