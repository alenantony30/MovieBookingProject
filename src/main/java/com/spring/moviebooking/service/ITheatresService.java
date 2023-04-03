package com.spring.moviebooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.entity.Theatres;


public interface ITheatresService {
	
	public List<Theatres> getTheatres();
	
	public List<Theatres>searchTheatresByKeyword(String keyword);

}
