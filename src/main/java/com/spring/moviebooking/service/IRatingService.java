package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.dto.RatingsDTO;
import com.spring.moviebooking.entity.Ratings;
import com.spring.moviebooking.exception.MovieException;

public interface IRatingService {

	
	public Ratings addReview(RatingsDTO ratingsDTO) throws MovieException;
	
	public List<Ratings> getRatingsBymovieName(String moviename);
}
