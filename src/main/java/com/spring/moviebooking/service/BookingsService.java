package com.spring.moviebooking.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
		Shows show = showsRepo.findById(bookings.getShowId()).orElse(null);

		if (show != null) {
			Date showDate = show.getShowDate();
			Time showTime = show.getShowTime();
			String paymentMethod = bookings.getPaymentMethod();

			LocalDateTime showDateAndTime = showDate.toLocalDate().atTime(showTime.toLocalTime());
			LocalDateTime now = LocalDateTime.now();

			int theatreId = show.getTheatre().getTheatreId();
			int showId = show.getShowId();
			int seatNo = bookings.getSeatNo();
			Long noOfBookedSeats = repo.getBookedCount(showId);
			Long totalCapacity = theatresRepo.getTotalCapacity(theatreId);
			int availableSeats = (int) (totalCapacity - noOfBookedSeats);
			Long alreadyBooked = repo.getSeatNo(showId, seatNo);

			String customerId = SecurityContextHolder.getContext().getAuthentication().getName();

			/*
			 * System.out.println("No of bookings= " + noOfBookedSeats);
			 * System.out.println("capacity= " + totalCapacity);
			 * System.out.println("Available seats = " + availableSeats);
			 * System.out.println("Already booked = " + alreadyBooked);
			 * System.out.println("current time = " + now);
			 * System.out.println("show time = " + showDateAndTime);
			 */

			if (availableSeats > 0) {

				if (alreadyBooked == 0) {

					if (seatNo <= totalCapacity && seatNo > 0) {

						if (showDateAndTime.isAfter(now)) {

							if (paymentMethod.equals("CC") || paymentMethod.equals("DC")
									|| paymentMethod.equals("UPI")) {

								Bookings details = new Bookings();

								System.out.println(show.getMovie().getMovieTitle());
								show.setAvailableSeats(availableSeats - 1);

								details.setSeatNo(bookings.getSeatNo());
								details.setBookingDate(Date.valueOf(now.toLocalDate()));
								details.setCustomerId(customerId);
								details.setPaymentMethod(bookings.getPaymentMethod());
								details.setShow(show);
								details.setTotalAmount(bookings.getTotalAmount());

								return repo.save(details);
							} else {
								// payment method should be DD CD UPI
								System.out.println("DD,CD,UPI are only supported ,Payment method not supported");
								return null;
							}
						} else {
							// film already started
							System.out.println("Film Already started");
							return null;
						}
					} else {
						// Invalid Seat number
						System.out.println("Invaild Seat Number");
						return null;
					}
				} else {
					// seat already booked
					System.out.println("Seat already booked");
					return null;
				}
			} else {
				System.out.println("House full");
				return null;// house full

			}
		} else {
			System.out.println("Invalid ShowID");
			return null;
		}
	}

	@Override
	public List<Bookings> getAllBookings() {

		String customerId = SecurityContextHolder.getContext().getAuthentication().getName();
		return repo.findByCustomerId(customerId);
	}

	@Transactional
	@Override
	public String cancelBooking(int bookingId) {
		String customerId = SecurityContextHolder.getContext().getAuthentication().getName();

		Shows show = repo.getShowID(bookingId, customerId);
		System.out.println(show);
		if (show != null) {
			Date showDate = show.getShowDate();
			Time showTime = show.getShowTime();
			LocalDateTime showDateAndTime = showDate.toLocalDate().atTime(showTime.toLocalTime());
			LocalDateTime now = LocalDateTime.now();

			if (showDateAndTime.isAfter(now)) {
				
				repo.deleteByIdAndCustomerId(bookingId, customerId);
				return "Booking is cancelled and you will get the refund in 3 working days";

			} else {
			
				
				System.out.println("You cannot cancel the Show, its Already Started");
				return "You cannot cancel the Show, its Already Started";
			}
		} else {
			System.out.println("Cannot find a show with the booking Id");
			return "Cannot find a show with the booking Id";
		}

	}

}
