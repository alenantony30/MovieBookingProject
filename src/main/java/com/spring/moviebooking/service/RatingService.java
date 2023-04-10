package com.spring.moviebooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.dto.RatingsDTO;
import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.entity.Ratings;
import com.spring.moviebooking.exception.InvalidMovieException;
import com.spring.moviebooking.repository.IMoviesRepository;
import com.spring.moviebooking.repository.IRatingsRepository;

@Service
public class RatingService implements IRatingService {

	@Autowired
	IRatingsRepository ratingRepo;

	@Autowired
	IMoviesRepository movieRepo;

	@Override
	public Ratings addReview(RatingsDTO ratingsDTO) throws InvalidMovieException {
		// TODO Auto-generated method stub
		Movies movie = movieRepo.findById(ratingsDTO.getMovieId()).orElse(null);
		Double userRating = ratingsDTO.getRating();
		if (movie != null) {

			if (userRating >= 1 && userRating <= 10) {

				Ratings ratings = new Ratings();
				String customerId = SecurityContextHolder.getContext().getAuthentication().getName();
				ratings.setRating(userRating);
				ratings.setReview(ratingsDTO.getReview());
				ratings.setMovie(movie);
				ratings.setCustomerId(customerId);
				ratings = ratingRepo.save(ratings);
				
				Double totalRating=ratingRepo.getTotalRatingByMovie(movie.getMovieTitle());
				movie.setRating(totalRating);
				movieRepo.save(movie);
				
				return ratings;
			}else {
				System.out.println("Rating should be between 1 and 10");
				throw (new InvalidMovieException("Rating should be between 1 and 10"));
				
			}

		} else {

			System.out.println("Cannot find the movie");
			throw (new InvalidMovieException("Cannot find the movie"));
			// return null;
		}
	}

	@Override
	public List<Ratings> getRatingsBymovieName(String moviename) {
		// TODO Auto-generated method stub
		return ratingRepo.findRatingsByMovieTitle(moviename.toLowerCase());
	}

}
