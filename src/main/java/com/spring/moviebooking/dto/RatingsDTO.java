package com.spring.moviebooking.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spring.moviebooking.entity.Movies;
import com.spring.moviebooking.entity.Ratings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingsDTO {
	

	int  movieId;

	Double rating;

	String review;


}
