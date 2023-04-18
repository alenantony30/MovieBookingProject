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

import com.spring.moviebooking.entity.Theatres;
import com.spring.moviebooking.service.ITheatresService;

@RestController
@RequestMapping("/movies")
public class TheatresController {

	@Autowired
	ITheatresService theatreServices;
	
	@GetMapping("/searchTheatres/{keyword}")
	public List<Theatres> searchTheatres(@PathVariable String keyword) {
		return theatreServices.searchTheatresByKeyword(keyword);
	}
	
}
