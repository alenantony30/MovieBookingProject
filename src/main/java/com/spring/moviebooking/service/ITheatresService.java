package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.entity.Theatres;


public interface ITheatresService {
	
	public List<Theatres> getTheatres();
	
	public List<Theatres>searchTheatresByKeyword(String keyword);

}
