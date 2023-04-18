/* @Author : Alen Antony
 * 10-04-2023
 */

package com.spring.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.exception.InvalidMovieException;
import com.spring.moviebooking.service.IBookingsService;

@RestController
@RequestMapping("/movies")
public class BookingController {
	
	@Autowired
	IBookingsService bookingServices;

	@PostMapping("/bookticket")
	public Bookings bookTicket(@RequestBody BookingsDTO booking) throws InvalidMovieException {
		return bookingServices.bookTicket(booking);
	}

	@GetMapping("/getmybookinghistory")
	public List<Bookings> getAllBookings() {
		return bookingServices.getAllMyBookings();
	}

	@DeleteMapping("/cancelbooking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) throws InvalidMovieException {
		return bookingServices.cancelMyBooking(bookingId);

	}

	
}
