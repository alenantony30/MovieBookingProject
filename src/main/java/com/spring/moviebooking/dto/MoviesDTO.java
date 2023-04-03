package com.spring.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoviesDTO {

		int movieId;
		String movieTitle;
		String genre;
		String director;
		int duration;
		double rating;
	
}
