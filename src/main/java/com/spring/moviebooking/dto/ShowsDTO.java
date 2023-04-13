package com.spring.moviebooking.dto;

import java.sql.Date;
import java.sql.Time;

import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.entity.Theatres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ShowsDTO {

	private int showId;
	private Date showDate;
	private Time showTime;
	private int availableSeats;

}
