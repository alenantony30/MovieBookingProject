package com.spring.moviebooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.moviebooking.entity.Shows;


public interface IShowsService {
	
	public List<Shows> getShows();

}
