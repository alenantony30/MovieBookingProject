package com.spring.moviebooking.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingsDTO {

	
	
	//String customerId;
	private int showId;
//	Date bookingDate;
	//Double totalAmount;
	private String paymentMethod;
	private int seatNo;
}
