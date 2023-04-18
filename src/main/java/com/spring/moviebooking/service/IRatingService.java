/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.dto.RatingsDTO;
import com.spring.moviebooking.entity.Ratings;
import com.spring.moviebooking.exception.InvalidMovieException;

public interface IRatingService {

	
	public Ratings addReview(RatingsDTO ratingsDTO) throws InvalidMovieException;
	
	public List<Ratings> getRatingsBymovieName(String moviename);
}
