package com.spring.moviebooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.moviebooking.entity.Theatres;
import com.spring.moviebooking.repository.ITheatresRepository;
@Service
public class TheatresServices implements ITheatresService {

	@Autowired
	ITheatresRepository repo;
	
	@Override
	public List<Theatres> getTheatres() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<Theatres> searchTheatresByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchTheatresByKeyword(keyword.toLowerCase());
	}

}
