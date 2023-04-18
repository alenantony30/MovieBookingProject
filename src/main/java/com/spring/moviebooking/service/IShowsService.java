/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.entity.Shows;


public interface IShowsService {
	
	public List<Shows> getShows();
	
	public List<Shows> searchShowsByTitle(String movieTitle);

}
