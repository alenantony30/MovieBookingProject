package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.entity.Movies;


public interface IMoviesServices {
	
	public List<Movies> getMovies();
	
	public List<Movies>searchMoviesByKeyword(String keyword);
	
	


}
