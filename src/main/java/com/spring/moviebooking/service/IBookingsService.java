/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.exception.InvalidMovieException;

public interface IBookingsService {
	
	public List<Bookings> getAll();
	
	public Bookings bookTicket(BookingsDTO bookings) throws InvalidMovieException;
	
	
	public List<Bookings> getAllMyBookings();
	
	public String cancelMyBooking(int bookingId) throws  InvalidMovieException;
	

}
