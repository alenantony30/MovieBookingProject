/* @Author : Alen Antony
 * 02-04-2023
 */


package com.spring.moviebooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.entity.Shows;
import com.spring.moviebooking.repository.IShowsRepository;
@Service
public class ShowsServices implements IShowsService {

	@Autowired
	IShowsRepository repo;
	
	@Override
	public List<Shows> getShows() {
		
		return repo.findAll();
	}

	@Override
	public List<Shows> searchShowsByTitle(String movieTitle) {
		
		return repo.searchShowsByTitle(movieTitle.toLowerCase());
	}

}
