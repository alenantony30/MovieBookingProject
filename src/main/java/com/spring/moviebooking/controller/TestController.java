package com.spring.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.moviebooking.dto.BookingsDTO;
import com.spring.moviebooking.dto.MoviesShowsTheatres;
import com.spring.moviebooking.entity.Bookings;
import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.entity.Shows;
import com.spring.moviebooking.entity.Theatres;
import com.spring.moviebooking.service.IBookingsService;
import com.spring.moviebooking.service.IMoviesServices;
import com.spring.moviebooking.service.IShowsService;
import com.spring.moviebooking.service.ITheatresService;

@RestController
@RequestMapping("/movies")
public class TestController {

	
	@Autowired
	IMoviesServices movieServies;
	
	@Autowired
	IShowsService showServices;
	
	@Autowired
	ITheatresService theatreServices;
	
	@Autowired
	IBookingsService bookingServices;
	
	//@GetMapping("/getmovies")
	public List<Movies> getMovies(){
		return movieServies.getMovies();
	}
	
	//@GetMapping("/getbookings")
	public List<Bookings> getBookings(){
		return bookingServices.getAll();
	}
	
	//@GetMapping("/getshows")
	public List<Shows> getShows(){
		return showServices.getShows();
	}
	
	//@GetMapping("/gettheatres")
	public List<Theatres> getTheatres(){
		return theatreServices.getTheatres();
	}
	
	
	@GetMapping("/searchmovies/{keyword}")
	public List<Movies> searchMovies(@PathVariable String keyword){
		return movieServies.searchMoviesByKeyword(keyword);
	}
	
	@GetMapping("/searchTheatres/{keyword}")
	public List<Theatres> searchTheatres(@PathVariable String keyword){
		return theatreServices.searchTheatresByKeyword(keyword);
	}
	
	@GetMapping("/getmoviesandtheatres/{keyword}")
	public List<MoviesShowsTheatres> getMoviesByTheatres(@PathVariable String keyword){
		return movieServies.findMovieDetailsByTitle(keyword);
	}
	
	@PostMapping("/bookticket")
	public Bookings bookTicket(@RequestBody BookingsDTO booking) {
		return bookingServices.bookTicket(booking);
	}
	
}
