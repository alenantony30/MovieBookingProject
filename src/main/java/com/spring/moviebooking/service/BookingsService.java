/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.entity.Shows;
import com.spring.moviebooking.exception.InvalidMovieException;
import com.spring.moviebooking.repository.IBookingsRepository;
import com.spring.moviebooking.repository.IShowsRepository;
import com.spring.moviebooking.repository.ITheatresRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
	public Bookings bookTicket(BookingsDTO bookings) throws InvalidMovieException {

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
			double ticketPrice = show.getTheatre().getTicketPrice();
			Long noOfBookedSeats = bookigRepo.getBookedCount(showId);
			Long totalCapacity = theatresRepo.getTotalCapacity(theatreId);

			int availableSeats = (int) (totalCapacity - noOfBookedSeats);
			Long alreadyBooked = bookigRepo.getSeatNo(showId, seatNo);

			String customerId = SecurityContextHolder.getContext().getAuthentication().getName();

			if (availableSeats > 0) {

				if (alreadyBooked == 0) {

					if (seatNo <= totalCapacity && seatNo > 0) {

						if (showDateAndTime.isAfter(now)) {

							if (paymentMethod.equals("CC") || paymentMethod.equals("DC")
									|| paymentMethod.equals("UPI")) {

								Bookings details = new Bookings();

								log.error(show.getMovie().getMovieTitle());
								show.setAvailableSeats(availableSeats - 1);

								details.setSeatNo(bookings.getSeatNo());
								details.setBookingDate(Date.valueOf(now.toLocalDate()));
								details.setCustomerId(customerId);
								details.setPaymentMethod(bookings.getPaymentMethod());
								details.setShow(show);
								details.setTotalAmount(ticketPrice);

								return bookigRepo.save(details);
							} else {

								log.error("DC,CC,UPI are only supported ,Payment method not supported");
								throw (new InvalidMovieException(
										"DC,CC,UPI are only supported ,Payment method not supported"));

							}
						} else {

							log.error("Film Already started");
							throw (new InvalidMovieException("Film Already started"));

						}
					} else {

						log.error("Invaild Seat Number");
						throw (new InvalidMovieException("Invaild Seat Number"));

					}
				} else {

					log.error("Seat already booked");
					throw (new InvalidMovieException("Seat already booked"));

				}
			} else {
				log.error("House full");
				throw (new InvalidMovieException("House full"));

			}
		} else {
			log.error("Invalid ShowID");
			throw (new InvalidMovieException("Invalid ShowID"));

		}
	}

	@Override
	public List<Bookings> getAllMyBookings() {

		String customerId = SecurityContextHolder.getContext().getAuthentication().getName();
		return bookigRepo.findByCustomerId(customerId);
	}

	@Transactional
	@Override
	public String cancelMyBooking(int bookingId) throws InvalidMovieException {
		String customerId = SecurityContextHolder.getContext().getAuthentication().getName();

		Shows show = bookigRepo.getShow(bookingId, customerId);
		log.error(show);
		if (show != null) {
			Date showDate = show.getShowDate();
			Time showTime = show.getShowTime();
			LocalDateTime showDateAndTime = showDate.toLocalDate().atTime(showTime.toLocalTime());
			LocalDateTime now = LocalDateTime.now();
			int availableSeats = show.getAvailableSeats();

			if (showDateAndTime.isAfter(now)) {

				bookigRepo.deleteByIdAndCustomerId(bookingId, customerId);
				show.setAvailableSeats(availableSeats + 1);
				showsRepo.save(show);
				return "Booking is cancelled and you will get the refund in 3 working days";

			} else {

				log.error("You cannot cancel the Show, its Already Started");
				throw (new InvalidMovieException("You cannot cancel the Show, its Already Started"));

			}
		} else {

			log.error("Cannot find a show with the booking Id");
			throw (new InvalidMovieException("Cannot find a show with the booking Id"));

		}

	}

}
