package com.spring.moviebooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.repository.IMoviesRepository;

@Service
public class MoviesService implements IMoviesServices {

	@Autowired
	IMoviesRepository repo;

	@Override
	public List<Movies> getMovies() {
		
		return repo.findAll();
	}

	@Override
	public List<Movies> searchMoviesByKeyword(String keyword) {
		
		return repo.searchMoviesByKeyword(keyword.toLowerCase());
	}



}
