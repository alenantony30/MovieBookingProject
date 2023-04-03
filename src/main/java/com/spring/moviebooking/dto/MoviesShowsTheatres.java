package com.spring.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoviesShowsTheatres {

	MoviesDTO movie;
	ShowsDTO show;
	TheatresDTO theatre;
}
