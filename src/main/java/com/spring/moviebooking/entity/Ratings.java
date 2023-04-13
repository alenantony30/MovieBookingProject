package com.spring.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="ratings")
public class Ratings {
	
	@Id
	@Column(name="rating_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ratingId;
	
	@Column(name="customer_id")
	private String customerId;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movies movie;
	
	@Column(name="rating")
	private Double rating;
	
	@Column(name="review")
	private String review;


}
