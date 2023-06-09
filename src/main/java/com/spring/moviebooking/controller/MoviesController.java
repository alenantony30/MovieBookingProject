/* @Author : Alen Antony
 * 10-04-2023
 */

package com.spring.moviebooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.service.IMoviesServices;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	@Autowired
	IMoviesServices movieServies;

	@GetMapping("/searchmovies/{keyword}")
	public List<Movies> searchMovies(@PathVariable String keyword) {

		return movieServies.searchMoviesByKeyword(keyword);
	}

}
