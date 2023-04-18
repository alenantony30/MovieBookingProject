/* @Author : Alen Antony
 * 02-04-2023
 */

package com.spring.moviebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MoviesDTO {

	private int movieId;
	private String movieTitle;
	private String genre;
	private String director;
	private int duration;
	private double rating;
	
}
