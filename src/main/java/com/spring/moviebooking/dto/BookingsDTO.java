package com.spring.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingsDTO {

	private int showId;
	private String paymentMethod;
	private int seatNo;
}
