package com.spring.moviebooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.entity.Shows;

public interface IBookingsRepository extends JpaRepository<Bookings, Integer> {

	//To cancel a booking
	@Modifying
	@Query("DELETE FROM Bookings b  WHERE b.bookingId = :bookingId AND b.customerId=:customerId ")
	void deleteByIdAndCustomerId(@Param("bookingId") int bookingId, @Param("customerId") String customerId);

	//To get all my bookings
	public List<Bookings> findByCustomerId(String customerId);

	//To find number of tickets sold out for a show
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.show.id = :showId")
	public Long getBookedCount(@Param("showId") int showId);

	//To check whether seat is already booked or not
	@Query("SELECT COUNT(b) FROM Bookings b WHERE b.show.id = :showId AND b.seatNo = :seatNo")
	public Long getSeatNo(@Param("showId") int showId, @Param("seatNo") int seatNo);
	
	//To get the show from the booking id for canceling the booking, To check whether show is started or not
	@Query("SELECT b.show FROM Bookings b WHERE b.bookingId = :bookingId AND b.customerId=:customerId")
	public Shows getShow(@Param("bookingId")int bookingId, @Param("customerId") String customerId);
}
