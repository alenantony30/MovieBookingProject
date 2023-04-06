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
	int showId;
//	Date bookingDate;
	//Double totalAmount;
	String paymentMethod;
	int seatNo;
}
