/* @Author : Alen Antony
 * 10-04-2023
 */

package com.spring.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.moviebooking.dto.RatingsDTO;
import com.spring.moviebooking.entity.Ratings;
import com.spring.moviebooking.exception.InvalidMovieException;
import com.spring.moviebooking.service.IRatingService;

@RestController
@RequestMapping("/movies")
public class RatingController {


	@Autowired
	IRatingService ratingServices;
	
	
	@PostMapping("/ratemovie")
	public Ratings rateMovie(@RequestBody RatingsDTO ratings) throws InvalidMovieException {
		return ratingServices.addReview(ratings);
	}
	
	@GetMapping("/getratingsbymovie/{movieName}")
	public List<Ratings> getRatingsByMovie(@PathVariable String movieName){
		return ratingServices.getRatingsBymovieName(movieName);
	}
}
