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
	private int movieId;
	@Column(name="movie_title")
	private String movieTitle;
	private String genre;
	private String director;
	private int duration;
	private double rating;

}
