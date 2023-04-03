package com.spring.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="movies")
public class Movies {
	@Id
	@Column(name="movie_id")
	int movieId;
	@Column(name="movie_title")
	String movieTitle;
	String genre;
	String director;
	int duration;
	double rating;

}
