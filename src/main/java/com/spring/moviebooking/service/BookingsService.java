package com.spring.moviebooking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.entity.Shows;
import com.spring.moviebooking.repository.IBookingsRepository;
import com.spring.moviebooking.repository.IShowsRepository;
import com.spring.moviebooking.repository.ITheatresRepository;

@Service
public class BookingsService implements IBookingsService {

	@Autowired
	IBookingsRepository repo;

	@Autowired
	IShowsRepository showsRepo;

	@Autowired
	ITheatresRepository theatresRepo;

	@Override
	public List<Bookings> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Bookings bookTicket(BookingsDTO bookings) {
		// TODO Auto-generated method stub
		Shows show = showsRepo.findById(bookings.getShow_id()).orElse(null);
		int theatreId = show.getTheatre().getTheatreId();
		int showId = show.getShowId();
		int seatNo = bookings.getSeatNo();
		
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		
		
		Long noOfBookedSeats = repo.getBookedCount(showId);
		Long totalCapacity = theatresRepo.getTotalCapacity(theatreId);
		int availableSeats = (int) (totalCapacity - noOfBookedSeats);
		Long alreadyBooked = repo.getSeatNo(showId, seatNo);

		System.out.println("No of bookings= " + noOfBookedSeats);
		System.out.println("capacity= " + totalCapacity);
		System.out.println("Available seats = " + availableSeats);
		System.out.println("Already booked = " + alreadyBooked);

		if (availableSeats > 0 && alreadyBooked == 0 && seatNo <= totalCapacity && seatNo > 0) {

			Bookings details = new Bookings();

			System.out.println(show.getMovie().getMovieTitle());
			show.setAvailableSeats(availableSeats - 1);

			details.setSeatNo(bookings.getSeatNo());
			details.setBookingDate(bookings.getBookingDate());
			details.setCustomerId(bookings.getCustomerId());
			details.setPaymentMethod(bookings.getPaymentMethod());
			details.setShow(show);
			details.setTotalAmount(bookings.getTotalAmount());

			return repo.save(details);
		} else {
			System.out.println("Booking not possible");
			return null;// throw error seats not available

		}
	}

}
