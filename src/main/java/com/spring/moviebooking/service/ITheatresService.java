/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.service;

import java.util.List;

import com.spring.moviebooking.entity.Theatres;


public interface ITheatresService {
	
	public List<Theatres> getTheatres();
	
	public List<Theatres>searchTheatresByKeyword(String keyword);

}
