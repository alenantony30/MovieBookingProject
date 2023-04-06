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
import com.spring.moviebooking.exception.MovieException;
import com.spring.moviebooking.repository.IBookingsRepository;
import com.spring.moviebooking.repository.IShowsRepository;
import com.spring.moviebooking.repository.ITheatresRepository;

@Service
public class BookingsService implements IBookingsService {

	@Autowired
	IBookingsRepository bookigRepo;

	@Autowired
	IShowsRepository showsRepo;

	@Autowired
	ITheatresRepository theatresRepo;

	@Override
	public List<Bookings> getAll() {

		return bookigRepo.findAll();
	
	}

	@Override
	public Bookings bookTicket(BookingsDTO bookings) throws MovieException {
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
			double ticketPrice=show.getTheatre().getTicketPrice();
			Long noOfBookedSeats = bookigRepo.getBookedCount(showId);
			Long totalCapacity = theatresRepo.getTotalCapacity(theatreId);
			int availableSeats = (int) (totalCapacity - noOfBookedSeats);
			Long alreadyBooked = bookigRepo.getSeatNo(showId, seatNo);

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
								details.setTotalAmount(ticketPrice);

								return bookigRepo.save(details);
							} else {
								
								// payment method should be DD CD UPI
								System.out.println("DC,CC,UPI are only supported ,Payment method not supported");
								throw(new MovieException("DC,CC,UPI are only supported ,Payment method not supported"));
								
							}
						} else {
							// film already started
							System.out.println("Film Already started");
							throw(new MovieException("Film Already started"));
							//return null;
						}
					} else {
						// Invalid Seat number
						System.out.println("Invaild Seat Number");
						throw(new MovieException("Invaild Seat Number"));
						//return null;
					}
				} else {
					// seat already booked
					System.out.println("Seat already booked");
					throw(new MovieException("Seat already booked"));
					//return null;
				}
			} else {
				System.out.println("House full");
				throw(new MovieException("House full"));
				//return null;// house full

			}
		} else {
			System.out.println("Invalid ShowID");
			throw(new MovieException("Invalid ShowID"));
			//return null;
		}
	}

	@Override
	public List<Bookings> getAllMyBookings() {

		String customerId = SecurityContextHolder.getContext().getAuthentication().getName();
		return bookigRepo.findByCustomerId(customerId);
	}

	@Transactional
	@Override
	public String cancelMyBooking(int bookingId) throws MovieException {
		String customerId = SecurityContextHolder.getContext().getAuthentication().getName();

		Shows show = bookigRepo.getShow(bookingId, customerId);
		System.out.println(show);
		if (show != null) {
			Date showDate = show.getShowDate();
			Time showTime = show.getShowTime();
			LocalDateTime showDateAndTime = showDate.toLocalDate().atTime(showTime.toLocalTime());
			LocalDateTime now = LocalDateTime.now();
			int availableSeats=show.getAvailableSeats();

			if (showDateAndTime.isAfter(now)) {
				
				
				bookigRepo.deleteByIdAndCustomerId(bookingId, customerId);
				show.setAvailableSeats(availableSeats+1);
				showsRepo.save(show);
				return "Booking is cancelled and you will get the refund in 3 working days";

			} else {
			
				
				System.out.println("You cannot cancel the Show, its Already Started");
				throw(new MovieException("You cannot cancel the Show, its Already Started"));
				//return "You cannot cancel the Show, its Already Started";
			}
		} else {
			System.out.println("Cannot find a show with the booking Id");
			throw(new MovieException("Cannot find a show with the booking Id"));
			//return "Cannot find a show with the booking Id";
		}

	}

}
