package com.spring.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.moviebooking.entity.Shows;
import com.spring.moviebooking.service.IShowsService;

@RestController
@RequestMapping("/movies")
public class ShowsController {

	@Autowired
	IShowsService showServices;
	
	@GetMapping("/getshows/{keyword}")
	public List<Shows> getShowsByMovies(@PathVariable String keyword) {
		return showServices.searchShowsByTitle(keyword);
	}
	
}
